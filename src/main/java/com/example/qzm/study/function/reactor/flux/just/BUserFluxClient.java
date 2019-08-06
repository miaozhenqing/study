package com.example.qzm.study.function.reactor.flux.just;

import reactor.core.publisher.Flux;

import java.util.function.Consumer;

/**
 * @ClassName FluxClient
 * @Description TODO
 * @Version 1.0
 **/
public class BUserFluxClient {
    public static void main(String[] args) {
        User user1 = new User("toooooom",10);
        User user2 = new User("jackkkkk",20);

        Flux<User> flux = Flux.just(user1,user2);
        Consumer<User> consumer = new Consumer<User>() {
            @Override
            public void accept(User user) {
                System.out.println(user.toString());
            }
        };
        Consumer<Throwable> errorConsumer = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable s) {
                System.out.println("异常："+ s.getMessage());
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
