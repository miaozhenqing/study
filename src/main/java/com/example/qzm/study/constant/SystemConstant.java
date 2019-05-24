package com.example.qzm.study.constant;

/**
 * @ClassName RedisKeyConstant
 * @Description TODO
 * @Version 1.0
 **/
public class SystemConstant {
    public static class RedisConfig{
        public static final String host = "127.0.0.1";
        public static final int port = 6379;
    }
    public static class RedisKey{
        public static final String KEY_MAP = "NAME_MAP";
        public static final String KEY_LIST = "KEY_LIST";
        public static final String KEY_HSET = "KEY_HSET";

        public static final String KEY_PUB_SUB = "KEY_PUB_SUB";

        public static final String KEY_REACTIVE = "KEY_REACTIVE";
    }
}
