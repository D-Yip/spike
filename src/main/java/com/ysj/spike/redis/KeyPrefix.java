package com.ysj.spike.redis;

public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
