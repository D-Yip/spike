package com.ysj.spike.redis;

public interface RedisService {

    <T> T get(String key, Class<T> clazz);

    <T> boolean set(String key, T value);
}
