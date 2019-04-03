package com.ysj.spike.redis;


public interface RedisService {

    /**
     * 获取单个对象
     * @param keyPrefix
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(KeyPrefix keyPrefix,String key, Class<T> clazz);

    /**
     * 设置单个对象
     * @param keyPrefix
     * @param key
     * @param value
     * @param <T>
     * @return
     */
    <T> boolean set(KeyPrefix keyPrefix,String key, T value);

    /**
     * 判断是否存在
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    <T> boolean exists(KeyPrefix keyPrefix,String key);

    /**
     * 增加
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    <T> Long incr(KeyPrefix keyPrefix,  String key);

    /**
     * 减少
     * @param keyPrefix
     * @param key
     * @param <T>
     * @return
     */
    <T> Long decr(KeyPrefix keyPrefix,  String key);

    /**
     * 删除key
     * @param keyPrefix
     * @param key
     * @return
     */
    boolean delete(KeyPrefix keyPrefix, String key);

}
