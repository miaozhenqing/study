package com.example.qzm.study.function.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestHeapOOM
 * @Description 内存溢出(堆) 1.不断创建对象；2.保证对象不被gc；3.设置堆大小参数-Xms:10m -Xmx10
 * @Version 1.0
 **/
public class TestHeapOOM {
    public static void main(String[] args) {
        List<Role> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(new Role(i++));
//            Role role = new Role(i++);
            System.out.println(i);
        }
    }

    public static class Role {
        private int id;

        public Role(int id) {
            this.id = id;
        }
    }
}
