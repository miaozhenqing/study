package com.example.qzm.study.function.reactor.reactor_official_example;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @ClassName Sca
 * @Description TODO
 * @Version 1.0
 **/
public class SchedulersClient {
    public static void main(String[] args) {
        test();
    }
    public static void test(){
        Flux.create(sink -> {
            sink.next(Thread.currentThread().getName());
            sink.complete();
        })
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .toStream()
                .forEach(System.out::println);

    }
}
