package com.example.qzm.study.function.reactor.reactor_official_example;

import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * reactor官方例子
 * https://www.infoq.com/articles/reactor-by-example/
 * @Version 1.0
 **/
public class FluxClient2 {
    private static List<String> words = Arrays.asList(
            "the",
            "quick",
            "brown",
            "fox",
            "jumped",
            "over",
            "the",
            "lazy",
            "dog"
    );

    public static void main(String[] args) {
        firstEmitting();
    }


    public static void firstEmitting() {
        Mono<String> a = Mono.just("oops I'm late")
                .delaySubscription(Duration.ofMillis(450));
        Flux<String> b = Flux.just("let's get", "the party", "started")
                .delaySubscription(Duration.ofMillis(400));

        Flux.first(a, b)
                .toIterable()
                .forEach(System.out::println);
    }

    public static void blocks() {
        Flux<String> flux=Mono.just("hello").concatWith(Mono.just("world").delaySubscription(Duration.ofMillis(2000)));
        flux.toStream().forEach(System.out::println);
    }
    /**
     * 测试终止得太早，只能打印Hello,而不能打印world
     * 解决方法 1：参考void blocks()
     */
    public static void shortCircuit() {
        Flux<String> helloPauseWorld =
                Mono.just("Hello").concatWith(
                        Mono.just("world").delaySubscription(Duration.ofMillis(500))
                );
        helloPauseWorld.subscribe(System.out::println);
    }

    /**
     * concatWith(mono)添加一个mono
     */
    public static void restoringMissingLetter() {
        Mono<String> missing = Mono.just("s");
        Flux<String> allLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                //在a~z里添加缺失的s
                .concatWith(missing)
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        allLetters.subscribe(System.out::println);
    }

    /**
     * 把每个单词分解成字母，去重，升序，和数字合并
     */
    public static void findingMissingLetter() {
        Flux<String> manyLetters = Flux
                .fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .distinct()
                .sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) -> String.format("%2d. %s", count, string));

        manyLetters.subscribe(System.out::println);
    }

}
