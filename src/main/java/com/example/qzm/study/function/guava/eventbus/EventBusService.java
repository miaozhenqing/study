package com.example.qzm.study.function.guava.eventbus;

import com.example.qzm.study.function.guava.eventbus.handler.AbsEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @ClassName EventPoster
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
@Component
public class EventBusService {
    @Autowired
    private List<AbsEventHandler> absEventHandlers;

    private BaseEventBusService service;
    @PostConstruct
    public void init(){
        service = BaseEventBusService.newBuilder()
                .createSyncEventBus()
                .asyncEventBusThread(2)
                .eventSubscribe(absEventHandlers.toArray())
                .build();
        log.info("Logic事件功能服务已加载.....");
    }

    /**
     * 同步事件发布
     */
    public void publish(Object event) {
        service.publish(event);
    }

    /**
     * 异步事件发布
     */
    public void asyncPublish(Object event) {
        service.asyncPublish(event);
    }

    /**
     * 服务进程关闭
     */
    public void serverShutDown(){
        log.info("关闭 [EventBus服务] [开始] ");
        this.service.shutDown();
        log.info("关闭 [EventBus服务] [结束] ");
    }
}
