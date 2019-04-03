package com.ysj.spike.service;

import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.SpikeOrder;
import com.ysj.spike.vo.GoodsVO;

public interface OrderService {
    SpikeOrder getSpikeOrderByUserIdGoodsId(long userId, long goodsId);

    OrderInfo createOrder(long userId, GoodsVO goodsVO);

    OrderInfo getOrderById(long orderId);
}
