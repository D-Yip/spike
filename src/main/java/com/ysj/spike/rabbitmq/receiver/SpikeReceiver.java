package com.ysj.spike.rabbitmq.receiver;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.base.Result;
import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.SpikeOrder;
import com.ysj.spike.rabbitmq.MQReceiver;
import com.ysj.spike.rabbitmq.config.MQConfig;
import com.ysj.spike.rabbitmq.message.SpikeMessage;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.service.OrderService;
import com.ysj.spike.service.SpikeService;
import com.ysj.spike.utils.TransformUtil;
import com.ysj.spike.vo.GoodsVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class SpikeReceiver {
    private static final Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SpikeService spikeService;

    @RabbitListener(queues = MQConfig.SPIKE_QUEUE)
    public void receive(String message) {
        log.info("receive message : " + message);
        SpikeMessage spikeMessage = TransformUtil.stringToBean(message,SpikeMessage.class);
        long userId = spikeMessage.getUserId();
        long goodsId = spikeMessage.getGoodsId();

        // 判断库存
        GoodsVO goodsVO = goodsService.getGoodsVOByGoodsId(goodsId);
        Integer stockCount = goodsVO.getStockCount();
        if (stockCount <= 0) {
            return;
        }
        // 判断是否已经秒杀到了
        SpikeOrder spikeOrder = orderService.getSpikeOrderByUserIdGoodsId(userId, goodsId);
        if (spikeOrder != null) {
            return;
        }

        //减库存 下订单 写入秒杀订单
        spikeService.spike(userId, goodsVO);
    }

}
