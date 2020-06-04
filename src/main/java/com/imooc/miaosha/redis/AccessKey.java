package com.imooc.miaosha.redis; /**
 * Created with IntelliJ IDEA By fty on 2020/6/2
 */

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/6/2 15:17
 * @Version V1.0
 **/
public class AccessKey extends BasePrefix {
    private AccessKey( int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static AccessKey withExpire(int expireSeconds) {
        return new AccessKey(expireSeconds, "access");
    }

}
