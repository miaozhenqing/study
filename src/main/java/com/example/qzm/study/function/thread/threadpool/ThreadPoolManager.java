package com.example.qzm.study.function.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @ClassName ThreadPoolManager
 * 线程池管理
 * @Version 1.0
 **/
public class ThreadPoolManager {
    public static void main(String[] args) {
        newSingleThreadExecutor();
    }

    /**
     * 创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
     * 那么就会回收部分空闲（60秒不执行任务）的线程，
     * 当任务数增加时，此线程池又可以智能的添加新线程来处理任务。
     * 此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
     */
    public static void newCachedThreadPool(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(()-> System.out.println("newCachedThreadPool"));
    }
    /**
     * 创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。
     * 线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程.
     *
     * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
     */
    public static void newFixedThreadPool(){
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.execute(()-> System.out.println("newFixedThreadPool"));
    }

    /**
     * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行.
     *
     * 创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
     */
    public static void newScheduledThreadPool(){
        ExecutorService threadPool = Executors.newScheduledThreadPool(1);
        threadPool.execute(()-> System.out.println("newScheduledThreadPool"));
    }

    /**
     *创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。
     * 如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。
     * 此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
     */
    public static void newSingleThreadExecutor(){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        threadPool.execute(runnable);
    }
    static Runnable runnable=new Runnable() {
        @Override
        public void run() {
            System.out.println("newSingleThreadExecutor");
        }
    };
}
