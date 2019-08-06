package com.example.qzm.study.function.reactor.error;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Random;

/**
 * 错误处理
 **/
public class ErrorClient {
    public static void main(String[] args) {
        onErrorResume();
    }
    /**
     * onErrorResume方法能够在收到错误信号的时候提供一个新的数据流
     */
    public static void onErrorResume(){
        Flux.range(1, 6)
                .map(i -> 10/(i-3))
                // 提供新的数据流
                .onErrorResume(e -> Mono.just(new Random().nextInt(6)))
                .map(i -> i*i)
                .subscribe(System.out::println, System.err::println);
    }
    /**
     * onErrorReturn方法能够在收到错误信号的时候提供一个缺省值
     */
    public static void onErrorReturn(){
        Flux.range(1, 6)
                .map(i -> 10/(i-3))
                .onErrorReturn(0)
                .map(i -> i*i)
                .subscribe(System.out::println, System.err::println);
    }
    /**
     * i=3时 输出错误
     */
    public static void testErrorHandling(){
        Flux.range(1, 6)
                .map(i -> 10/(i-3))
                .map(i -> i*i)
                .subscribe(System.out::println, System.err::println);
    }
}
