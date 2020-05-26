package com.imooc.miaosha.vo;

import com.imooc.miaosha.domain.MiaoshaUser;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/4/24 16:38
 * @Version V1.0
 **/
public class GoodsDetailVo {

    private int miaoshaStatus = 0;
    private int remainSeconds = 0;
    private  GoodsVo goods;
    private MiaoshaUser user;
    public MiaoshaUser getUser() {
        return user;
    }

    public void setUser(MiaoshaUser user) {
        this.user = user;
    }

    public int getMiaoshaStatus() {
        return miaoshaStatus;
    }

    public void setMiaoshaStatus(int miaoshaStatus) {
        this.miaoshaStatus = miaoshaStatus;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goodsVo) {
        this.goods = goodsVo;
    }
}
