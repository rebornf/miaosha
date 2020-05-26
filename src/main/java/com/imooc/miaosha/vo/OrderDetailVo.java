package com.imooc.miaosha.vo;

import com.imooc.miaosha.domain.OrderInfo;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/5/3 10:34
 * @Version V1.0
 **/
public class OrderDetailVo {
    private GoodsVo  goods ;
    private OrderInfo order ;

    public GoodsVo getGoods() {
        return goods;
    }

    public void setGoods(GoodsVo goods) {
        this.goods = goods;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }
}
