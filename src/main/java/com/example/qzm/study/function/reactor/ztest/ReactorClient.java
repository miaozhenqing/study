package com.example.qzm.study.function.reactor.ztest;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * @ClassName ReactorClient
 * @Description TODO
 * @Version 1.0
 **/
public class ReactorClient {
    public static void main(String[] args) {
        Flux.fromArray(new Integer[]{1,2,3,4,5,6,7,8,9})
                .map(integer -> String.valueOf(integer)+integer)
                .publishOn(Schedulers.single())
                .subscribe(s -> System.out.println(s));
    }
}
