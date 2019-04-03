package com.ysj.spike.vo;

import com.ysj.spike.domain.OrderInfo;

public class OrderDetailVO {

    private GoodsVO goods;

    private OrderInfo orderInfo;

    public GoodsVO getGoods() {
        return goods;
    }

    public void setGoods(GoodsVO goods) {
        this.goods = goods;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
