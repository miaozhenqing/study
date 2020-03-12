package com.example.qzm.study.function.test.collection.list;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static com.example.qzm.study.function.reactor.flux.FluxClient.test1;

/**
 * @ClassName TestListRemoveClient
 * @Description TODO
 * @Version 1.0
 **/
public class MapClient {
    private static final Logger logger = LoggerFactory.getLogger(MapClient.class);

    public static void main(String[] args) {
        testRemoveHashMap();
    }



    public static void testRemoveHashMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "11111");
        map.put(2, "22222");
        map.put(3, "33333");
        map.put(4, "44444");
    }


}
