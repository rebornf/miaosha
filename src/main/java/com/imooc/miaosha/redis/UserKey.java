package com.imooc.miaosha.redis;

import com.imooc.miaosha.domain.User;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/4/18 22:25
 * @Version V1.0
 **/
public class UserKey extends BasePrefix {
    private  UserKey(String prefix){
        super(prefix);
    }
    public static UserKey getById =new UserKey("id");
    public static UserKey getByName =new UserKey("name");
}
