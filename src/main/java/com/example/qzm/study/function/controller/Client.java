package com.example.qzm.study.function.controller;

import com.example.qzm.study.function.guava.eventbus.EventBusService;
import com.example.qzm.study.function.guava.eventbus.event.LoginEvent;
import com.example.qzm.study.function.guava.eventbus.event.LogoutEvent;
import com.example.qzm.study.function.redis.lettuce.TestRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName Client
 * 测试入口
 * @Version 1.0
 **/
@RestController
@RequestMapping("/client")
public class Client {
    @Autowired
    private EventBusService eventBusService;
    @Autowired
    private TestRedisService testRedisService;
    @RequestMapping("/event/publish")
    public void test(){
        LoginEvent event=new LoginEvent(1,"tom");
        eventBusService.publish(event);

        LogoutEvent event1=new LogoutEvent(2,"merry");
        eventBusService.asyncPublish(event1);
    }

    @RequestMapping("/redis/hmset")
    public void testRedis(){
        testRedisService.hmset("1993","Tom");
    }

    @RequestMapping("/redis/lpush")
    public void lpush(){
        testRedisService.lpush();
    }
    @RequestMapping("/redis/lpushx")
    public void lpushx(){
        testRedisService.lpushx();
    }
    @RequestMapping("/redis/hgetall")
    public void hgetall(){
        testRedisService.hgetall();
    }
    @RequestMapping("/redis/setPubSub")
    public void setPubSub(){
        testRedisService.setPubSub();
    }
    @RequestMapping("/redis/reactive")
    public void reactive(){
        testRedisService.reactive();
    }
}
