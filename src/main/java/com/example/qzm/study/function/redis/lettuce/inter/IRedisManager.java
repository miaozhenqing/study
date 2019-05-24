package com.example.qzm.study.function.redis.lettuce.inter;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;

public interface IRedisManager<K, V> {
    /**同步*/
    RedisCommands<K, V> redisCommands() ;
    /**异步*/
    RedisAsyncCommands<K, V> redisAsyncCommands() ;
    /**发布订阅*/
    RedisPubSubAsyncCommands<K, V> pubSubAsyncCommands();
    /**反应式*/
    RedisReactiveCommands<K, V> redisReactiveCommands();
}
