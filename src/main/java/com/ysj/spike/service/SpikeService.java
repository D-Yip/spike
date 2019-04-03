package com.ysj.spike.service;

import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.vo.GoodsVO;

public interface SpikeService {
    OrderInfo spike(long userId, GoodsVO goodsVO);
}
