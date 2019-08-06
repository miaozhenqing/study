package com.example.qzm.study.function.reactor.backpressure;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;

/**
 * 背压（回压）功能测试
 * <p>
 * Flux.range是一个快的Publisher；
 * 在每次request的时候打印request个数；
 * 通过重写BaseSubscriber的方法来自定义Subscriber；
 * hookOnSubscribe定义在订阅的时候执行的操作；
 * 订阅时首先向上游请求1个元素；
 * hookOnNext定义每次在收到一个元素的时候的操作；
 * sleep 1秒钟来模拟慢的Subscriber；
 * 打印收到的元素；
 * 每次处理完1个元素后再请求1个。
 **/
public class BackPressureClient {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnRequest(value -> System.out.println("request value = " + value))
                .subscribe(new BaseSubscriber<Integer>() {
                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        System.out.println("onSubscribe...subscription=" + subscription);
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("onNext...value=" + value);
                        request(1);
                    }
                });

    }
}
