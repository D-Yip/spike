package com.ysj.spike.redis.impl;

public class OrderKey extends BasePrefix{

    public OrderKey(String prefix) {
        super(prefix);
    }

    public static OrderKey getSpikeOrderByUidGid = new OrderKey("spikeUidGid");
}
