package com.example.qzm.study.function.redis.lettuce;

import com.example.qzm.study.constant.SystemConstant;
import com.example.qzm.study.function.redis.lettuce.inter.IRedisPubSubListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @ClassName CRedisPubSubListener
 * @Description TODO
 * @Version 1.0
 **/
@Service
@Slf4j
public class CRedisPubSubListener implements IRedisPubSubListener {
    @Override
    public String[] keys() {
        String key = SystemConstant.RedisKey.KEY_PUB_SUB;
        return new String[]{key};
    }

    @Override
    public void message(Object o, Object o2) {
        log.debug("message...o={}, o2={}", o, o2);
    }

    @Override
    public void message(Object o, Object k1, Object o2) {
        log.debug("message...o={},k1={}, o2={}", o, k1, o2);
    }

    @Override
    public void subscribed(Object o, long l) {
        log.debug("subscribed...");
    }

    @Override
    public void psubscribed(Object o, long l) {
        log.debug("psubscribed...");
    }

    @Override
    public void unsubscribed(Object o, long l) {
        log.debug("unsubscribed...");
    }

    @Override
    public void punsubscribed(Object o, long l) {
        log.debug("punsubscribed...");
    }
}
