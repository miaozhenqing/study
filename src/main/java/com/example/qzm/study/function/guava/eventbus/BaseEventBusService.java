package com.example.qzm.study.function.guava.eventbus;


import com.example.qzm.study.util.ExecutorServiceShutDownHook;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.slf4j.MDC;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 系统事件服务
 * Created by Stone Mack on 2018/5/8.
 */
public final class BaseEventBusService {

    private final EventBus syncEventBus;
    private final EventBus asyncEventBus;
    private final ExecutorServiceShutDownHook hook;
    private BaseEventBusService(final EventBus syncEventBus, final EventBus asyncEventBus, ExecutorServiceShutDownHook hook) {
        this.syncEventBus = syncEventBus;
        this.asyncEventBus = asyncEventBus;
        this.hook = hook;
    }
    public static void preCall(Object event) {
    }

    public static void postCall() {
    }
    /**
     * 同步事件发布
     *
     * @param event 事件
     */
    public void publish(Object event) {
        checkNotNull(event);
        try {
            preCall(event);
            this.syncEventBus.post(event);
        }finally {
            postCall();
        }
    }

    /**
     * 异步事件发布
     *
     * @param event 事件
     */
    public void asyncPublish(Object event) {
        checkNotNull(event);
        this.asyncEventBus.post(event);
    }
    /**
     * 异步事件的线程关闭
     */
    public void shutDown(){
        if(this.hook == null){
            return;
        }
        // 关闭
        this.hook.shutDown();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private EventBus syncEventBus;
        private EventBus asyncEventBus;
        private List<Object> subscribeList;
        private ExecutorServiceShutDownHook asyncHook;
        /**
         * 默认异步事件执行线程池
         */
        private int defaultAsyncEventBusThread = 2;

        public Builder asyncEventBusThread(final int nThread) {
            this.defaultAsyncEventBusThread = nThread;
            return this;
        }

        /**
         * 创建同步业务类型的EventBus服务类型
         *
         * @return
         */
        public Builder createSyncEventBus() {
            this.syncEventBus = new EventBus();
            return this;
        }

        /**
         * 创建一个异步处理事件的EventBus
         * @param identifier    EventBus标识
         * @param executor 事件执行者
         */
        public Builder createAsyncEventBus(String identifier, Executor executor) {
            checkNotNull(executor);
            if (executor == null) {

            }
            this.asyncEventBus = new AsyncEventBus(identifier, executor);
            if(executor instanceof ExecutorService){
                this.asyncHook = new ExecutorServiceShutDownHook((ExecutorService)executor);
            }
            return this;
        }

        /**
         * 事件订阅者注册
         */
        public Builder eventSubscribe(Object subscriber) {
            checkNotNull(subscriber);
            if (this.subscribeList == null) {
                this.subscribeList = new LinkedList<>();
            }
            subscribeList.add(subscriber);
//            syncEventBus.register(subscriber);
//            asyncEventBus.register(subscriber);
            return this;
        }

        /**
         * 事件订阅者注册
         */
        public Builder eventSubscribe(Object... subscribers) {
            for (Object subscriber : subscribers) {
                checkNotNull(subscriber);
                this.eventSubscribe(subscriber);
            }
            return this;
        }

        /**
         * 构建event服务对象
         *
         * @return
         */
        public BaseEventBusService build() {
            if (this.syncEventBus == null) {
                this.syncEventBus = new EventBus();
            }
            if (this.asyncEventBus == null) {
                this.defaultAsyncEventBus();
            }

            if (this.subscribeList != null) {
                // 把订阅者注册到syncEventBus与asyncEventBus上面
                this.subscribeList.forEach(subscribe -> {
                    this.syncEventBus.register(subscribe);
                    this.asyncEventBus.register(subscribe);
                });
            }
            return new BaseEventBusService(this.syncEventBus, this.asyncEventBus,this.asyncHook);
        }

        /**
         * 创建默认的异步事件执行线程池
         */
        private void defaultAsyncEventBus() {
            // 默认执行异步事件的线程池
            final ExecutorService executorService = Executors.newFixedThreadPool(defaultAsyncEventBusThread, new DefaultAsyncEventBusThreadFactory("default-async-eventbus-bus-"));
            // 线程关闭的钩子
            this.asyncHook = new ExecutorServiceShutDownHook (executorService);
            this.asyncEventBus = new AsyncEventBus(executorService);
        }

        static class DefaultAsyncEventBusThreadFactory implements ThreadFactory {
            private static final AtomicInteger poolNumber = new AtomicInteger(1);
            private final ThreadGroup group;
            private final AtomicInteger threadNumber = new AtomicInteger(1);
            private final String namePrefix;

            DefaultAsyncEventBusThreadFactory(String namePrefix) {
                SecurityManager s = System.getSecurityManager();
                group = (s != null) ? s.getThreadGroup() :
                        Thread.currentThread().getThreadGroup();
                this.namePrefix = namePrefix + poolNumber.getAndIncrement() + "-thread-";
            }
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(group, r,
                        namePrefix + threadNumber.getAndIncrement(),
                        0);
                if (t.isDaemon()){
                    t.setDaemon(false);
                }
                if (t.getPriority() != Thread.NORM_PRIORITY){
                    t.setPriority(Thread.NORM_PRIORITY);
                }
                return t;
            }
        }
    }

    /**
     * 空对象检测
     *
     * @throws NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        }
        return reference;
    }
}
