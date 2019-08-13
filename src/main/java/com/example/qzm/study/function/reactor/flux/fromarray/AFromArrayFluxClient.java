package com.example.qzm.study.function.reactor.flux.fromarray;

import com.example.qzm.study.function.reactor.flux.just.User;
import reactor.core.publisher.Flux;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.Consumer;

/**
 * @ClassName FluxClient
 * @Description TODO
 * @Version 1.0
 **/
public class AFromArrayFluxClient {
    public static void main(String[] args) {
        User[] userArray = new User[2];
        userArray[0] = new User("toooooom", 10);
        userArray[1] = new User("jackkkkk", 20);

        Flux<User> flux = Flux.fromArray(userArray);
        Consumer<User> consumer = new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user.toString());
            }
        };
        Consumer<Throwable> errorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable s) {
                System.out.println("异常：" + s.getMessage());
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("。。。完成。。。");
            }
        });
        flux.subscribe(consumer, errorConsumer, thread);

    }
}
