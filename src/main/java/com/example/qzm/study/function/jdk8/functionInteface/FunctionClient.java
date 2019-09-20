package com.example.qzm.study.function.jdk8.functionInteface;

import java.util.function.Function;

/**
 * Function<T,R> 接受一个参数输入，返回一个结果
 * @Version 1.0
 **/
public class FunctionClient {
    public static void main(String[] args) {
        Function<Integer, String> function = new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                if (integer == 0) {
                    return "error";
                } else {
                    return "success";
                }
            }
        };
        String result = function.apply(0);
        System.out.println(result);
    }
}
