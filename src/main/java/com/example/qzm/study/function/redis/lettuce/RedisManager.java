package com.example.qzm.study.function.redis.lettuce;

import com.example.qzm.study.constant.SystemConstant;
import com.example.qzm.study.function.redis.lettuce.inter.IRedisManager;
import com.example.qzm.study.function.redis.lettuce.inter.IRedisPubSubListener;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.protocol.RedisCommand;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.async.RedisPubSubAsyncCommands;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @ClassName RedisService
 * lettuce redis 服务类
 * @Version 1.0
 **/
@Component
public class RedisManager implements IRedisManager {
    private RedisClient redisClient;
    private RedisCommands<String, String> redisCommands;
    private RedisAsyncCommands<String, String> redisAsyncCommands;
    private RedisPubSubAsyncCommands<String, String> pubSubAsyncCommands;
    private StatefulRedisConnection<String, String> connection;
    private RedisReactiveCommands<String, String> redisReactiveCommands;
    @Autowired
    List<IRedisPubSubListener> redisPubSubListeners;

    @PostConstruct
    public void init() {
//        //初始化redis连接
//        this.initConnect(this.createClientResources(), RedisURI.create(SystemConstant.RedisConfig.host, SystemConstant.RedisConfig.port));
//        //初始化redis订阅发布
//        IRedisPubSubListener[] listeners = redisPubSubListeners.toArray(new IRedisPubSubListener[redisPubSubListeners.size()]);
//        this.initPubSub(listeners);
    }

    /**
     * redis连接初始化
     */
    public void initConnect(ClientResources clientResources, RedisURI redisURI) {
        redisClient = RedisClientProxy.builder().clientReources(clientResources).redisURI(redisURI).build();
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        this.connection = connection;
        this.redisCommands = connection.sync();
        this.redisAsyncCommands = connection.async();
        this.redisReactiveCommands = connection.reactive();
    }

    /**
     * 创建ClientResources
     */
    private ClientResources createClientResources() {
        int threadNum = 2;
        DefaultClientResources.Builder builder = DefaultClientResources.builder();
        builder.ioThreadPoolSize(threadNum);
        builder.computationThreadPoolSize(2);
        return builder.build();
    }

    /**
     * 订阅
     */
    public void initPubSub(IRedisPubSubListener[] listeners) {
        StatefulRedisPubSubConnection<String, String> pubSubConnection = this.redisClient.connectPubSub();
        this.pubSubAsyncCommands = pubSubConnection.async();
        for (IRedisPubSubListener listener : listeners) {
            pubSubConnection.addListener(listener);
            this.pubSubAsyncCommands.subscribe(listener.keys());
        }
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
    public RedisPubSubAsyncCommands<String, String> pubSubAsyncCommands() {
        return pubSubAsyncCommands;
    }

    @Override
    public RedisReactiveCommands<String, String> redisReactiveCommands() {
        return redisReactiveCommands;
    }
}
