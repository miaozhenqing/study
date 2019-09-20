package com.example.qzm.study.function.jdk8.functionInteface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Supplier<T> 无参数，返回一个结果
 *
 * @Version 1.0
 **/
public class SupplierClient {
    public static void main(String[] args) {
        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Supplier";
            }
        };
        System.out.println(supplier.get());
    }
}
