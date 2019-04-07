package com.ysj.spike.service;

import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.vo.GoodsVO;

public interface SpikeService {
    OrderInfo spike(long userId, GoodsVO goodsVO);

    long getSpikeResult(Long id, long goodsId);

    boolean checkPath(long userId, long goodsId, String path);

    String createSpikePath(long userId, long goodsId);
}
