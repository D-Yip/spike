package com.ysj.spike.service.impl;

import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.SpikeOrder;
import com.ysj.spike.redis.RedisService;
import com.ysj.spike.redis.impl.SpikeKey;
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

    @Autowired
    private RedisService redisService;

    @Override
    @Transactional
    public OrderInfo spike(long userId, GoodsVO goodsVO) {
        // 减库存 下订单 写入秒杀订单
        boolean succcess = goodsService.reduceStock(goodsVO);
        if (succcess) {
            OrderInfo orderInfo = orderService.createOrder(userId,goodsVO);
            return orderInfo;
        } else {
            setGoodsOver(goodsVO.getId());
            return null;
        }
    }


    @Override
    public long getSpikeResult(Long id, long goodsId) {
        SpikeOrder spikeOrder = orderService.getSpikeOrderByUserIdGoodsId(id, goodsId);
        if (spikeOrder != null) {
            return spikeOrder.getOrderId();
        } else {
            boolean isOver = getGoodsOver(goodsId);
            if (isOver) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private void setGoodsOver(Long goodsId) {
        redisService.set(SpikeKey.isGoodsOver,""+goodsId,true);
    }

    private boolean getGoodsOver(Long goodsId) {
        boolean exists = redisService.exists(SpikeKey.isGoodsOver, "" + goodsId);
        if (exists) {
            return true;
        }
        return false;
    }
}
