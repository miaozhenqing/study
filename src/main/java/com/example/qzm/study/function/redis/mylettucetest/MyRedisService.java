package com.example.qzm.study.function.redis.mylettucetest;

import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * @ClassName RedisService
 * @Description redis 服务
 * @Version 1.0
 **/
public class MyRedisService implements IMyRedisService<String, String> {
    private RedisCommands<String, String> redisCommands;
    private RedisAsyncCommands<String, String> redisAsyncCommands;
    private RedisPubSubCommands<String, String> redisPubSubCommands;
    private RedisPubSubAsyncCommands<String, String> redisPubSubAsyncCommands;
    private RedisReactiveCommands<String, String> redisReactiveCommands;

    public static final class Builder{

    }
    public Builder builder(){
        return new Builder();
    }

    @Override
    public RedisCommands<String, String> redisCommands() {
        return redisCommands;
    }

    @Override
    public RedisAsyncCommands<String, String> redisAsyncCommands() {
        return redisAsyncCommands;
    }

    @Override
    public RedisPubSubCommands<String, String> redisPubSubCommands() {
        return redisPubSubCommands;
    }

    @Override
    public RedisPubSubAsyncCommands<String, String> redisPubSubAsyncCommands() {
        return redisPubSubAsyncCommands;
    }

    @Override
    public RedisReactiveCommands<String, String> redisReactiveCommands() {
        return redisReactiveCommands;
    }
}
