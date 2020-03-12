package com.example.qzm.study.function.test.collection.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName TestListRemoveClient
 * @Description TODO
 * @Version 1.0
 **/
public class ListClient {
    private static final Logger logger = LoggerFactory.getLogger(ListClient.class);

    public static void main(String[] args) {
        testRemoveList();
    }

    public static void testRemoveList() {
//        List<String> list = new ArrayList<String>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            String str = (String) iterator.next();
            if (str.equals("d")) {
                list.remove(str);
//                break;
            } else {
                System.out.println(str);
            }
        }
        logger.debug("testRemoveList...list={}", list);
    }

    public static void testMap2() {
        ArrayList<Integer> arrayList = new ArrayList();
        List<Integer> list = Collections.synchronizedList(arrayList);
        list.add(1);
    }
    public static void list1() {
        //初始化：默认大小为10
        List<Integer> list = new ArrayList<>();
        //添加：1.确保容量足够，不够则扩容（容量增加原来的一半，右移）；2.添加进去
        list.add(11);
        list.add(22);
        //迭代器删除
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            iterator.next();
        }
        System.out.println(list);
    }

}
