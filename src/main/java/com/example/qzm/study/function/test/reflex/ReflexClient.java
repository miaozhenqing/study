package com.example.qzm.study.function.test.reflex;


/**
 * @ClassName ReflexClient
 * 反射
 * @Version 1.0
 **/
public class ReflexClient {
    public static void main(String[] args) {
        try {
            //1.
//            User user1 = User.class.newInstance();
//            user1.setName("tom");
//            user1.setCount(1);
//            System.out.println(user1.getName());

            //2.
            User user2 = User.class.getConstructor().newInstance();
            System.out.println(user2.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
