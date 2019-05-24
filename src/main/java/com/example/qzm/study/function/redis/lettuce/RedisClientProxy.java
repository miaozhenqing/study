package com.example.qzm.study.function.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;

/**
 * @ClassName RedisClientInstance
 * @Description TODO
 * @Version 1.0
 **/
public class RedisClientProxy extends RedisClient {

    private RedisClientProxy(ClientResources clientResources, RedisURI redisURI) {
        super(clientResources, redisURI);
    }
    public static Builder builder(){
        return new Builder();
    }
    public static class Builder {
        private ClientResources clientResources;
        private RedisURI redisURI;

        public Builder clientReources(ClientResources clientResources) {
            this.clientResources = clientResources;
            return this;
        }

        public Builder redisURI(RedisURI redisURI) {
            this.redisURI = redisURI;
            return this;
        }

        public RedisClientProxy build() {
            return new RedisClientProxy(clientResources, redisURI);
        }
    }

}
