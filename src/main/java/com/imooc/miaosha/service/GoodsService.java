package com.imooc.miaosha.service;

import com.imooc.miaosha.dao.GoodsDao;
import com.imooc.miaosha.domain.Goods;
import com.imooc.miaosha.domain.MiaoshaGoods;
import com.imooc.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author fty
 * @Description TODO
 * @Date 2020/4/14 23:17
 * @Version V1.0
 **/
@Service
public class GoodsService {

    @Autowired
    GoodsDao goodsDao ;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodId(long goodsId) {

        return goodsDao.getGoodsVoByGoodsId(goodsId);

    }

    public void reduceStock(GoodsVo goods){
        MiaoshaGoods g =new MiaoshaGoods();
        g.setGoodsId(goods.getId());
        goodsDao.reduceStock(g);
    }
}
