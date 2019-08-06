package com.example.qzm.study.function.reactor.flux.just;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

/**
 * @ClassName FluxClient
 * @Description TODO
 * @Version 1.0
 **/
public class AStringFluxClient {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("Hello", "World", "nihao");
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("正常=" + s);
            }
        };
        Consumer<Throwable> errorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable s) {
                System.out.println("异常："+ s.getMessage());
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("。。。完成。。。");
            }
        });
        flux.subscribe(consumer, errorConsumer, thread);
    }
}
