package com.example.qzm.study.function.jvm.common;

/**
 * @ClassName TestClient1
 * @Description 局部变量表Slot复用对垃圾收集的影响之一
 * -XX:+PrintGCDetails 打印GC日志
 * @Version 1.0
 **/
public class TestClient1 {
    public static void main(String[] args) {
        {
            byte[] bytes = new byte[64 * 1024 * 1024];
        }
        int a = 1;
        System.gc();

    }
}
