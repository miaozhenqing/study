package com.example.qzm.study.function.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName TestHeapOOM
 * @Description 内存溢出(栈) 1.增加栈帧中本地变量表的长度；2.设置栈大小参数
 * @Version 1.0
 **/
public class TestStackOOM {
    public static void main(String[] args) {
        Role role = new Role();
        role.incre();
    }

    public static class Role {
        private int id;

        public void incre() {
            this.id++;
            System.out.println(id);
            this.incre();
        }
    }
}
