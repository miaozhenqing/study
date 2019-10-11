package com.example.qzm.study.function.redis.lettuce;

import com.example.qzm.study.constant.SystemConstant;
import io.lettuce.core.KeyValue;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @ClassName TestRedisService
 * @Description 测试redisService功能
 * @Version 1.0
 **/
@Service
public class TestRedisService {

    @Autowired
    private RedisManager redisManager;

    /**
     * 同步+异步的设值和取值
     * map
     */
    public void hmset(String id, String name) {
        String key = SystemConstant.RedisKey.KEY_MAP;
        //同步
        Map<String, String> map2 = new HashMap<>();
        map2.put(id + "2", name + "2");
        redisManager.redisCommands().hmset(key, map2);
        //获取结果
        String[] fields = new String[]{id};
        List<KeyValue<String, String>> result = redisManager.redisCommands().hmget(key, fields);
        for (KeyValue keyValue : result) {
            System.out.println("key   = " + keyValue.getKey());
            System.out.println("value = " + keyValue.getValue());
            System.out.println("---------------------");
        }

        //异步
        Map<String, String> map = new HashMap<>();
        map.put(id, name);
        BiConsumer<String, Throwable> consumer = new BiConsumer<String, Throwable>() {
            @Override
            public void accept(String s, Throwable throwable) {
                if ("OK".equals(s)) {
                    System.out.println("");
                }
                if (throwable != null) {
                    throwable.printStackTrace();
                }
            }
        };
        redisManager.redisAsyncCommands().hmset(key, map).whenCompleteAsync(consumer);
    }

    /**
     * list
     */
    public String lpush() {
        String key = SystemConstant.RedisKey.KEY_LIST;
        String[] fields = new String[]{"aa", "bb", "cc", "dd"};
        //同步
        long result = redisManager.redisCommands().lpush(key, fields);
        String dd = redisManager.redisCommands().lpop(key);
        String cc = redisManager.redisCommands().lpop(key);
        String bb = redisManager.redisCommands().lpop(key);
        String aa = redisManager.redisCommands().lpop(key);
        System.out.println(dd + " -- " + cc + " -- " + bb + " -- " + aa);

        return fields.toString();
    }

    /**
     * 1 lpushx 每次只能插入一个value。
     * 2 listKeyName必须已存在redis数据库中,并且数据类型必须为list(列表)。
     * 3 如果执行成功  则返回最新的listKeyName中的元素个数。
     */
    public void lpushx() {
        String key = SystemConstant.RedisKey.KEY_LIST;
        redisManager.redisAsyncCommands().lpush(key, new String[]{"first"}).whenCompleteAsync(new BiConsumer<Long, Throwable>() {
            @Override
            public void accept(Long aLong, Throwable throwable) {
                String[] fields = new String[]{"aa"};
                //异步
                long result = redisManager.redisCommands().lpushx(key, fields);
                System.out.println(result);
            }
        });
        //lambda表达式
        redisManager.redisAsyncCommands().lpush(key, new String[]{"first"}).whenCompleteAsync((aLong, throwable) -> {
            String[] fields = new String[]{"aa11"};
            //异步
            redisManager.redisCommands().lpushx(key, fields);
        });
    }

    public void hgetall() {
        String key = SystemConstant.RedisKey.KEY_HSET;
        redisManager.redisCommands().hset(key, "1994", "cherry");
        redisManager.redisCommands().hset(key, "1994", "cherry2");
        redisManager.redisCommands().hset(key, "1995", "jack");

        String cherry2 = redisManager.redisCommands().hget(key, "1994");
        Map<String, String> map = redisManager.redisCommands().hgetall(key);
        System.out.println(cherry2 + " -- " + map.values());
    }

    /**
     * 发送订阅内容
     */
    public void setPubSub() {
        String key = SystemConstant.RedisKey.KEY_PUB_SUB;
        redisManager.redisCommands().publish(key, "tooooooooooooom");
    }

    public void reactive() {
        String key = SystemConstant.RedisKey.KEY_REACTIVE;
        Map<String, String> map = new HashMap<>();
        map.put("id", "452229");
        map.put("name", "tooooooooooooooom");
        redisManager.redisReactiveCommands().hmset(key, map).subscribe(s -> System.out.println(s));

    }

    /**
     * 验证同步和异步执行的线程
     */
    @PostConstruct
    public void threadTest() {
        redisManager.redisCommands().set("threadTestKey1", "threadTestValue1");
        System.out.println("redisCommands thread:" + Thread.currentThread().getName());
        redisManager.redisAsyncCommands().set("threadTestKey2", "threadTestValue2").whenCompleteAsync((result, throwable) -> {
            if (throwable != null) {
                throwable.printStackTrace();
            }
            System.out.println("whenCompleteAsync thread:" + Thread.currentThread().getName());
        });
        redisManager.redisAsyncCommands().set("threadTestKey3", "threadTestValue3").whenComplete((result, throwss) -> {
            if (throwss != null) {
                throwss.printStackTrace();
            }
            System.out.println("whenComplete thread:" + Thread.currentThread().getName());
        });
    }
}
