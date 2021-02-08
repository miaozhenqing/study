package com.example.qzm.study.function.jvm.jdkutils;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestForJConsole
 * @Description 用于jconsole线程监控测试
 * @Version 1.0
 **/
public class Test2ForJConsole {

    public static void addObj() throws Throwable{
        List<Obj> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Thread.sleep(50);
            list.add(new Obj());
        }
        System.gc();
    }

    public static class Obj {
        public byte[] bytes = new byte[64 * 1024];
    }

    public static void main(String[] args)throws Throwable {
        try {
//            Thread.sleep(10000);
            Test2ForJConsole.addObj();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}