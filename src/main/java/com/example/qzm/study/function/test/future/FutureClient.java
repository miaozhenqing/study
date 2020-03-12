package com.example.qzm.study.function.test.future;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @ClassName FutureClient
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
public class FutureClient {
    public static void main(String[] args) {
        testCompletableFuture();
    }

    public static void testCompletableFuture() {
        CompletableFuture<String> future = new CompletableFuture();
        future.whenCompleteAsync((s, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            } else {
                System.out.println("testCompletableFuture...s=" + s);
            }
        });
        future.complete("success");
    }

    public static void callableTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.debug("Callable...call is running");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "finished";
            }
        });
        try {
            log.debug("start...");
            String get = future.get();
            log.debug("end...get={}", get);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void futureTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future future = executorService.submit(() -> {
            log.debug("future...start running");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("future...end running");
        });

        while (!future.isDone()) {
            log.debug("future...waiting");
        }
        log.debug("future...done");
        executorService.shutdown();
    }

    private static ChannelFuture channelFuture() {
        ChannelFuture channelFuture = new ChannelFuture() {
            @Override
            public Channel channel() {
                return null;
            }

            @Override
            public ChannelFuture addListener(GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>> genericFutureListener) {
                return null;
            }

            @Override
            public ChannelFuture addListeners(GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>... genericFutureListeners) {
                return null;
            }

            @Override
            public ChannelFuture removeListener(GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>> genericFutureListener) {
                return null;
            }

            @Override
            public ChannelFuture removeListeners(GenericFutureListener<? extends io.netty.util.concurrent.Future<? super Void>>... genericFutureListeners) {
                return null;
            }

            @Override
            public ChannelFuture sync() throws InterruptedException {
                return null;
            }

            @Override
            public ChannelFuture syncUninterruptibly() {
                return null;
            }

            @Override
            public ChannelFuture await() throws InterruptedException {
                return null;
            }

            @Override
            public ChannelFuture awaitUninterruptibly() {
                return null;
            }

            @Override
            public boolean isVoid() {
                return false;
            }

            @Override
            public boolean isSuccess() {
                return false;
            }

            @Override
            public boolean isCancellable() {
                return false;
            }

            @Override
            public Throwable cause() {
                return null;
            }

            @Override
            public boolean await(long l, TimeUnit timeUnit) throws InterruptedException {
                return false;
            }

            @Override
            public boolean await(long l) throws InterruptedException {
                return false;
            }

            @Override
            public boolean awaitUninterruptibly(long l, TimeUnit timeUnit) {
                return false;
            }

            @Override
            public boolean awaitUninterruptibly(long l) {
                return false;
            }

            @Override
            public Void getNow() {
                return null;
            }

            @Override
            public boolean cancel(boolean b) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public Void get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public Void get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
        return channelFuture;
    }
}
