package com.imooc.miaosha.dao;


import com.imooc.miaosha.domain.MiaoshaMessageInfo;
import com.imooc.miaosha.domain.MiaoshaMessageUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MiaoShaMessageDao {
	
	@Select("select * from miaosha_message where messageid =  #{messageid}  ")
	public List<MiaoshaMessageInfo> listMiaoShaMessage(@Param("messageId") String messageId);
	@Select("<script>select * from miaosha_message_user where 1=1 <if test=\"messageId !=null \">and messageId = #{messageId} </if></script>")
	public List<MiaoshaMessageUser> listMiaoShaMessageUser(@Param("messageId") String messageId);
	@Insert("insert into miaosha_message (id , messageid ,content , create_time ,status,over_time,message_type ,send_type , good_name , price,messageHead)" +
			"value (#{id},#{messageId},#{content},#{createTime},#{status},#{overTime},#{messageType},#{sendType},#{goodName},#{price},#{messageHead}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void insertMiaoShaMessage(MiaoshaMessageInfo miaoShaMessage);

	@Insert("insert into miaosha_message_user (id , userid ,messageid , goodid ,orderid)" +
			"value (#{id},#{userId},#{messageId},#{goodId},#{orderId}) ")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public void insertMiaoShaMessageUser(MiaoshaMessageUser miaoShaMessageUser);


	@Select(" <script> select * from miaosha_message_user mmu , miaosha_message mm where " +
			" mmu.messageid = mm.messageid and  userid=${userId}  <if test=\"status !=null \">and status = #{status} </if></script> ")
	public List<MiaoshaMessageInfo> listMiaoShaMessageByUserId(@Param("userId") long userId, @Param("status") Integer status);
}
