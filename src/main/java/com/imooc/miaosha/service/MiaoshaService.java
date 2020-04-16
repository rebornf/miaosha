package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.GoodsDao;
import com.imooc.miaosha.domain.MiaoshaOrder;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.domain.OrderInfo;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/4/14 23:17
 * @Version V1.0
 **/
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService ;

    @Autowired
    OrderService orderService;

    @Transactional
    public OrderInfo miaosha (MiaoshaUser user ,GoodsVo goods){
        //减库存，下订单，写入秒杀订单
        goodsService.reduceStock(goods);
        //order_info miaosha_order
        return orderService.creatOrder(user,goods);
    }
}
