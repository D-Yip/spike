package com.ysj.spike.redis.impl;

public class SpikeKey extends BasePrefix {

    public SpikeKey(String prefix) {
        super(prefix);
    }

    public static SpikeKey isGoodsOver = new SpikeKey("go");
}
