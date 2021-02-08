package com.example.qzm.study.function.jvm.gc;

/**
 * @ClassName AllocationObjAndGc
 * @Description 分配对象，eden空间不足，发生minorGC
 * 设置堆内存参数为：-Xms20m -Xmx20m -Xmn10m,堆内库设置为20m，不可扩展。其中新生代10兆，老年代10兆
 * -XX:SurvivorRatio=8 eden区和一个survivorRatio比例为8:1（默认）
 * @Version 1.0
 **/
public class AllocationObjAndGc {
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) {
        //8m的eden区分配6m
        byte[] allocation1 = new byte[2 * _1M];
        byte[] allocation2 = new byte[2 * _1M];
        byte[] allocation3 = new byte[2 * _1M];
        //再分配4m的时候不够
        byte[] allocation4 = new byte[4 * _1M];

    }
}
