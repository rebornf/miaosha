package com.imooc.miaosha.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import com.imooc.miaosha.rabbitmq.MQSender;
import com.imooc.miaosha.result.SnowflakeIdWorker;
import com.imooc.miaosha.result.enums.MessageStatus;
import com.imooc.miaosha.util.SnowFlake;
import com.imooc.miaosha.vo.MiaoShaMessageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.miaosha.dao.MiaoshaUserDao;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.redis.MiaoshaUserKey;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.util.MD5Util;
import com.imooc.miaosha.util.UUIDUtil;
import com.imooc.miaosha.vo.LoginVo;

import java.util.Date;


/**
* @Description:
*
*/

@Service
public class MiaoshaUserService {
	
	
	public static final String COOKI_NAME_TOKEN = "token";
	private static Logger logger = LoggerFactory.getLogger(MiaoshaUserService.class);
	private SnowFlake snowFlake=new SnowFlake(2,3);
	
	@Autowired
	MiaoshaUserDao miaoshaUserDao;
	
	@Autowired
	RedisService redisService;

	@Autowired
	private MQSender sender ;
	
	public MiaoshaUser getById(long id) {
		//取缓存
		MiaoshaUser user =redisService.get(MiaoshaUserKey.getById,"id",MiaoshaUser.class);
		if (user  !=null){
			return user;
		}
		//取数据库
		user =miaoshaUserDao.getById(id);
		if (user !=null){
			redisService.set(MiaoshaUserKey.getById,""+id,user);
		}
		return  user;

	}


	public boolean updatePassword(String token ,long id ,String formPass){
		//取user
		MiaoshaUser user =getById(id);
		if (user ==null){
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}
		//更新数据库
		MiaoshaUser  toBeUpdate =new MiaoshaUser();
		toBeUpdate.setId(id);
		toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass,user.getSalt()));
		miaoshaUserDao.update(toBeUpdate);
		//处理缓存,也就是更新缓存
		redisService.delete(MiaoshaUserKey.getById,""+id);
		user.setPassword(toBeUpdate.getPassword());
		redisService.set(MiaoshaUserKey.token,token ,user);//更新token
		return  true;

	}


	public boolean register(HttpServletResponse response , String userName , String passWord , String salt) {
		MiaoshaUser miaoShaUser =  new MiaoshaUser();
		miaoShaUser.setNickname(userName);
		String DBPassWord =  MD5Util.formPassToDBPass(passWord , salt);
		miaoShaUser.setPassword(DBPassWord);
		miaoShaUser.setRegisterDate(new Date());
		miaoShaUser.setSalt(salt);
		miaoShaUser.setNickname(userName);
		try {
			miaoshaUserDao.insertMiaoShaUser(miaoShaUser);
			MiaoshaUser user = miaoshaUserDao.getByNickname(miaoShaUser.getNickname());
			if(user == null){
				return false;
			}

			MiaoShaMessageVo vo = new MiaoShaMessageVo();
			vo.setContent("尊敬的用户你好，你已经成功注册！");
			vo.setCreateTime(new Date());
			vo.setMessageId(SnowflakeIdWorker.getOrderId(0,0));
			vo.setSendType(0);
			vo.setStatus(0);
			vo.setMessageType(MessageStatus.messageType.system_message.ordinal());
			vo.setUserId(miaoShaUser.getId());
			vo.setMessageHead(MessageStatus.ContentEnum.system_message_register_head.getMessage());
			sender.sendRegisterMessage(vo);


			//生成cookie 将session返回游览器 分布式session
			String token= UUIDUtil.uuid();
			addCookie(response, token, user);
		} catch (Exception e) {
			logger.error("注册失败",e);
			return false;
		}
		return true;
	}
	

	public MiaoshaUser getByToken(HttpServletResponse response, String token) {
		if(StringUtils.isEmpty(token)) {
			return null;
		}
		MiaoshaUser user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
		//延长有效期
		if(user != null) {
			addCookie(response, token, user);
		}
		return user;
	}
	

	public boolean login(HttpServletResponse response, LoginVo loginVo) {
		if(loginVo == null) {
			throw new GlobalException(CodeMsg.SERVER_ERROR);
		}
		String mobile = loginVo.getMobile();
		String formPass = loginVo.getPassword();
		//判断手机号是否存在
		MiaoshaUser user = getById(Long.parseLong(mobile));
		if(user == null) {
			throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
		}
		//验证密码
		String dbPass = user.getPassword();
		String saltDB = user.getSalt();
		String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
		if(!calcPass.equals(dbPass)) {
			throw new GlobalException(CodeMsg.PASSWORD_ERROR);
		}
		/**登陆成功以后，
		*
		 */
		//生成cookie
		//这里用的是UUID,
		//String token  = UUIDUtil.uuid();
		String token = String.valueOf(snowFlake.nextId());

		addCookie(response, token, user);
		return true;
	}
	
	private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
		redisService.set(MiaoshaUserKey.token, token, user); //往缓存里面设置一下值
		Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);//生成一个新的cookie
		cookie.setMaxAge(MiaoshaUserKey.token.expireSeconds());
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
