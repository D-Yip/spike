package com.ysj.spike.controller;

import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.base.Result;
import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.SpikeOrder;
import com.ysj.spike.domain.User;
import com.ysj.spike.rabbitmq.MQSender;
import com.ysj.spike.rabbitmq.message.SpikeMessage;
import com.ysj.spike.rabbitmq.sender.SpikeSender;
import com.ysj.spike.redis.RedisService;
import com.ysj.spike.redis.impl.GoodsKey;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.service.OrderService;
import com.ysj.spike.service.SpikeService;
import com.ysj.spike.vo.GoodsVO;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/spike")
public class SpikeController implements InitializingBean {

    private Map<Long,Boolean> localOverMap = new HashMap<>();

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private SpikeService spikeService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private SpikeSender sender;

    /**
     * 系统初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVO> goodsList = goodsService.listGoodsVO();
        if (goodsList == null) {
            return;
        }
        for (GoodsVO goods : goodsList) {
            redisService.set(GoodsKey.getSpikeGoodsStock,""+goods.getId(),goods.getGoodsStock());
            localOverMap.put(goods.getId(),false);
        }

    }

    @GetMapping("/doSpike")
    public Result doSpike(@RequestParam("goodsId") long goodsId,@RequestParam("userId") long userId) {

        boolean isOver = localOverMap.get(goodsId);
        if (isOver) {
            return Result.error(CodeMsg.SPIKE_OVER);
        }

        // 判断库存
        Long stock = redisService.decr(GoodsKey.getSpikeGoodsStock, "" + goodsId);
        if (stock < 0) {
            localOverMap.put(goodsId,true);
            return Result.error(CodeMsg.SPIKE_OVER);
        }

        // 判断是否已经秒杀到了
        SpikeOrder spikeOrder = orderService.getSpikeOrderByUserIdGoodsId(userId, goodsId);
        if (spikeOrder != null) {
            return Result.error(CodeMsg.SPIKE_REPEATE);
        }

        //　发送一个秒杀message,入队
        SpikeMessage spikeMessage = new SpikeMessage();
        spikeMessage.setGoodsId(goodsId);
        spikeMessage.setUserId(userId);
        sender.sendSpikeMessage(spikeMessage);

        // 0-排队中
        return Result.success(0);
    }

    /**
     *
     * @param user
     * @param goodsId
     * @return orderId:成功　-１:失败 0:排队中
     */
    @GetMapping(value = "/result")
    public Result spikeResult(User user, @RequestParam("goodsId")long goodsId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result = spikeService.getSpikeResult(user.getId(),goodsId);
        return Result.success(result);
    }

}
