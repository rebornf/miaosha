package com.imooc.miaosha.access;

import com.imooc.miaosha.domain.MiaoshaUser;

public class UserContext {
	
	private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>(); //把用户保存到ThreadLocal当中
	
	public static void setUser(MiaoshaUser user) {
		userHolder.set(user);
	}
	
	public static MiaoshaUser getUser() {
		return userHolder.get();
	}

}


/*
* @Description: ThreadLocal 是多线程的时候，保证线程安全的一种方式，Threadlocal是和当前线程绑定的，往ThreadLocal里面放东西，其实是
* 放到当前线程里面来，如果是多线程的话，是不存在线程冲突这种情况的
* @Param:
* @return:
* @Author: fty
* @Date:
*/
