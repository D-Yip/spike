package com.ysj.spike.controller;

import com.sun.jdi.PrimitiveValue;
import com.ysj.spike.base.CodeMsg;
import com.ysj.spike.base.Result;
import com.ysj.spike.domain.OrderInfo;
import com.ysj.spike.domain.User;
import com.ysj.spike.service.GoodsService;
import com.ysj.spike.service.OrderService;
import com.ysj.spike.vo.GoodsVO;
import com.ysj.spike.vo.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/detail")
    public Result info(User user, @RequestParam("orderId")long orderId) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        OrderInfo orderInfo = orderService.getOrderById(orderId);
        if (orderInfo == null) {
            return Result.error(CodeMsg.ORDER_NOT_EXIST);
        }
        Long goodsId = orderInfo.getGoodsId();
        GoodsVO goods = goodsService.getGoodsVOByGoodsId(goodsId);
        OrderDetailVO orderDetail = new OrderDetailVO();
        orderDetail.setOrderInfo(orderInfo);
        orderDetail.setGoods(goods);
        return Result.success(orderDetail);
    }
}
