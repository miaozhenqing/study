package com.example.qzm.study.function.reactor.schedulers;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 *调度器与线程模型
 * @Version 1.0
 **/
public class SchedulersClient {
    public static void main(String[] args) {
        //当前线程
        Scheduler scheduler1 = Schedulers.immediate();
        //可重用的单线程。注意，这个方法对所有调用者都提供同一个线程来使用， 直到该调度器被废弃。
        // 如果你想使用独占的线程，请使用Schedulers.newSingle()；
        Scheduler scheduler2 = Schedulers.single();
        //弹性线程池（Schedulers.elastic()）。它根据需要创建一个线程池，重用空闲线程。线程池如果空闲时间过长 （默认为 60s）就会被废弃。
        // 对于 I/O 阻塞的场景比较适用。Schedulers.elastic()能够方便地给一个阻塞 的任务分配它自己的线程，从而不会妨碍其他任务和资源；
        Scheduler scheduler3 = Schedulers.elastic();
        //固定大小线程池（Schedulers.parallel()），所创建线程池的大小与CPU个数等同；
        Scheduler scheduler4 = Schedulers.elastic();
        //自定义线程池（Schedulers.fromExecutorService(ExecutorService)）基于自定义的ExecutorService创建 Scheduler（虽然不太建议，不过你也可以使用Executor来创建）。
        //Scheduler scheduler5 = Schedulers.fromExecutorService(ExecutorService)
    }
    public void describe(){

    }
}
