package com.example.qzm.study.function.reactor.flux.generate;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

/**
 * @ClassName GenerateClient
 * @Description TODO
 * @Version 1.0
 **/
public class GenerateClient {
    public static void main(String[] args) {
//        Flux.generate(sink->{sink.next("hello");sink.complete();}).subscribe(System.out::println);
        test("hello", new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
    public static void test(String name, Consumer<String> consumer){
        consumer.accept(name);

    }
}
