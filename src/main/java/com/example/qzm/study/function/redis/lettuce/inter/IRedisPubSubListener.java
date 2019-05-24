package com.example.qzm.study.function.redis.lettuce.inter;

import io.lettuce.core.pubsub.RedisPubSubListener;

/**
 * @ClassName IRedisPubSubListener
 * redis 监听器
 * @Version 1.0
 **/
public interface IRedisPubSubListener<K,V> extends RedisPubSubListener<K,V> {
    /**
     * 订阅的key
     */
    String[] keys();
}
