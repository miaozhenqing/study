package com.example.qzm.study.function.redis.mylettucetest;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * @ClassName IRedisService
 * @Description redis接口
 * @Version 1.0
 **/
public interface IMyRedisService<K,V> {
    /**同步接口*/
    RedisCommands<K,V> redisCommands();
    /**异步接口*/
    RedisAsyncCommands<K,V> redisAsyncCommands();
    /**发布订阅接口，同步*/
    RedisPubSubCommands<K,V> redisPubSubCommands();
    /**发布订阅接口，异步*/
    RedisPubSubAsyncCommands<K,V> redisPubSubAsyncCommands();
    /**流式接口*/
    RedisReactiveCommands<K,V> redisReactiveCommands();
}
