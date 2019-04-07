package com.ysj.spike.redis.impl;

public class SpikeKey extends BasePrefix {

    public SpikeKey(int expireSeconds,String prefix) {
        super(expireSeconds,prefix);
    }

    public static SpikeKey isGoodsOver = new SpikeKey(0,"go");
    public static SpikeKey getSpikePath = new SpikeKey(60,"sp");
}
