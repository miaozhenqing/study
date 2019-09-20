package com.example.qzm.study.function.jdk8.functionInteface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Consumer<T> 接受一个参数输入，无返回
 *
 * @Version 1.0
 **/
public class ConsumerClient {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("consumer...." + s);
            }
        };
        consumer.accept("tom");

        BiConsumer<String, String> biConsumer = new BiConsumer<String, String>() {
            @Override
            public void accept(String s, String s2) {
                System.out.println(s + "----" + s2);
            }
        };
        biConsumer.accept("tom", "jack");
    }
}
