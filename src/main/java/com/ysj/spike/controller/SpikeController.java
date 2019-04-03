package com.ysj.spike.controller;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.base.Result;
import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.SpikeOrder;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.service.OrderService;
import com.ysj.spike.service.SpikeService;
import com.ysj.spike.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/spike")
public class SpikeController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SpikeService spikeService;

    @GetMapping("/doSpike")
    public Result doSpike(@RequestParam("goodsId") long goodsId,@RequestParam("userId") long userId) {

        // 判断库存
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        Integer stockCount = goodsVO.getStockCount();
        if (stockCount <= 0) {
            return Result.error(CodeMsg.SPIKE_OVER);
        }
        // 判断是否已经秒杀到了
        SpikeOrder spikeOrder = orderService.getSpikeOrderByUserIdGoodsId(userId, goodsId);
        if (spikeOrder != null) {
            return Result.error(CodeMsg.SPIKE_REPEATE);
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = spikeService.spike(userId, goodsVO);
        if (orderInfo == null) {
            return Result.error(CodeMsg.SPIKE_OVER);
        }
        return Result.success(orderInfo);
    }
}
