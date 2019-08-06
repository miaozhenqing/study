package com.example.qzm.study.function.reactor.flux;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @ClassName FluxClient
 * @Description TODO
 * @Version 1.0
 **/
public class FluxClient2 {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("hello", "world", "nihao");
        flux.subscribe(new Consumer<String>() {
                           @Override
                           public void accept(String s) {
                               System.out.println("正常数据处理");
                           }
                       },
                new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        System.out.println("错误信号处理");
                    }
                },
                () -> System.out.println("完成信号处理"),
                new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) {
                        System.out.println("订阅发生时的处理逻辑");
                    }
                });

    }


}
