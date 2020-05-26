package com.imooc.miaosha.rabbitmq;

import com.imooc.miaosha.domain.MiaoshaOrder;
import com.imooc.miaosha.domain.MiaoshaUser;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/5/13 22:08
 * @Version V1.0
 **/
public class MiaoshaMessage {
    private MiaoshaUser  user;
    private long  goodsId;

    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }
}
