package com.ysj.spike.service.impl;

import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.service.OrderService;
import com.ysj.spike.service.SpikeService;
import com.ysj.spike.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpikeServiceImpl implements SpikeService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Override
    @Transactional
    public OrderInfo spike(long userId, GoodsVO goodsVO) {
        // 减库存 下订单 写入秒杀订单
        int i = goodsService.reduceStock(goodsVO);
        if (i == 0) {
            return null;
        }

        OrderInfo orderInfo = orderService.createOrder(userId,goodsVO);
        return orderInfo;
    }
}
