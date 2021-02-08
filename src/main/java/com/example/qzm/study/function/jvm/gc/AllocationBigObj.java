package com.example.qzm.study.function.jvm.gc;

/**
 * @ClassName AllocationObjAndGc
 * @Description 大对象直接分配给老年代
 * 设置对象大小阈值，超过直接分配到老年代(不能使用类似3m的写法):-XX:PretenureSizeThreshold=3145278
 * @Version 1.0
 **/
public class AllocationBigObj {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation4 = new byte[4 * _1M];

    }
}
