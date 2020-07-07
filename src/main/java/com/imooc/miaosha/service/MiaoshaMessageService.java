package com.imooc.miaosha.service; /**
 * Created with IntelliJ IDEA By fty on 2020/7/2
 */

import com.imooc.miaosha.dao.MiaoShaMessageDao;
import com.imooc.miaosha.domain.MiaoshaMessageInfo;
import com.imooc.miaosha.domain.MiaoshaMessageUser;
import com.imooc.miaosha.vo.MiaoShaMessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/7/2 20:46
 * @Version V1.0
 **/
@Service
public class MiaoshaMessageService {
    @Autowired
    private MiaoShaMessageDao messageDao;

    public List<MiaoshaMessageInfo> getmessageUserList(Long userId , Integer status ){
        return messageDao.listMiaoShaMessageByUserId(userId,status);
    }


    @Transactional(rollbackFor = Exception.class)
    public void insertMs(MiaoShaMessageVo miaoShaMessageVo){
        MiaoshaMessageUser mu = new MiaoshaMessageUser() ;
        mu.setUserId(miaoShaMessageVo.getUserId());
        mu.setMessageId(miaoShaMessageVo.getMessageId());
        messageDao.insertMiaoShaMessageUser(mu);
        MiaoshaMessageInfo miaoshaMessage = new MiaoshaMessageInfo();
        miaoshaMessage.setContent(miaoShaMessageVo.getContent());
//        miaoshaMessage.setCreateTime(new Date());
        miaoshaMessage.setStatus(miaoShaMessageVo.getStatus());
        miaoshaMessage.setMessageType(miaoShaMessageVo.getMessageType());
        miaoshaMessage.setSendType(miaoShaMessageVo.getSendType());
        miaoshaMessage.setMessageId(miaoShaMessageVo.getMessageId());
        miaoshaMessage.setCreateTime(new Date());
        miaoshaMessage.setMessageHead(miaoShaMessageVo.getMessageHead());
        messageDao.insertMiaoShaMessage(miaoshaMessage);
    }
}
