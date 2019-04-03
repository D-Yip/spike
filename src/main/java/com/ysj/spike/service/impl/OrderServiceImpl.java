package com.ysj.spike.service.impl;

import com.ysj.spike.dao.OrderDao;
import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.SpikeOrder;
import com.ysj.spike.service.OrderService;
import com.ysj.spike.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public SpikeOrder getSpikeOrderByUserIdGoodsId(long userId, long goodsId) {
        return orderDao.getSpikeOrderByUserIdGoodsId(userId,goodsId);
    }

    @Override
    @Transactional
    public OrderInfo createOrder(long userId, GoodsVO goodsVO) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goodsVO.getId());
        orderInfo.setGoodsName(goodsVO.getGoodsName());
        orderInfo.setGoodsPrice(goodsVO.getSpikePrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(userId);
        long orderId = orderDao.insert(orderInfo);
        SpikeOrder spikeOrder = new SpikeOrder();
        spikeOrder.setGoodsId(goodsVO.getId());
        spikeOrder.setOrderId(orderId);
        spikeOrder.setUserId(userId);
        orderDao.insertSpikeOrder(spikeOrder);
        return orderInfo;
    }
}
