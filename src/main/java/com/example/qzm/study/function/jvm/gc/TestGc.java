package com.example.qzm.study.function.jvm.gc;

/**
 * @ClassName TestGc
 * @Description 引用计数法
 * @Version 1.0
 **/
public class TestGc {
    public Object instance = null;
    private static final int _1m = 1024 * 1024;
    private byte[] bytes = new byte[2 * _1m];

    public static void test() {
        TestGc aa = new TestGc();
        TestGc bb = new TestGc();
        aa.instance = bb;
        bb.instance = aa;
        aa = null;
        bb = null;
        System.gc();
    }

    public static void main(String[] args) {
        TestGc.test();
    }

}
