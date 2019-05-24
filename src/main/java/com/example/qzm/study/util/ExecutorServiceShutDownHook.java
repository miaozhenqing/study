package com.example.qzm.study.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 服务器优雅停机
 * Created by Stone Mack on 2018/9/29.
 */
public final class ExecutorServiceShutDownHook {
    private static final Logger logger = LoggerFactory.getLogger(ExecutorServiceShutDownHook.class);
    // 默认开启
    private boolean waitForTasksToCompleteOnShutdown = true;
    // 默认30秒
    private int awaitTerminationSeconds = 30;
    private final ExecutorService executor;
    public ExecutorServiceShutDownHook(final ExecutorService executor){
        this.executor = executor;
    }
    /**
     * 设置进程关闭时是否等待任务任务完成
     * @param waitForTasksToCompleteOnShutdown
     */
    public void setWaitForTasksToCompleteOnShutdown(boolean waitForTasksToCompleteOnShutdown) {
        this.waitForTasksToCompleteOnShutdown = waitForTasksToCompleteOnShutdown;
    }

    /**
     * 进程关闭时等待多长时间关闭
     * @param awaitTerminationSeconds
     */
    public void setAwaitTerminationSeconds(int awaitTerminationSeconds) {
        this.awaitTerminationSeconds = awaitTerminationSeconds;
    }

    /**
     * Perform a shutdown on the underlying ExecutorService.
     * @see ExecutorService#shutdown()
     * @see ExecutorService#shutdownNow()
     */
    public void shutDown(){
        if(this.executor == null){
            return;
        }
        if (this.waitForTasksToCompleteOnShutdown) {
            this.executor.shutdown();
        }else {
            this.executor.shutdownNow();
        }
        this.awaitTerminationIfNecessary();
    }
    /**
     * Wait for the executor to terminate, according to the value of the
     * {@link #setAwaitTerminationSeconds "awaitTerminationSeconds"} property.
     */
    private void awaitTerminationIfNecessary() {
        if (this.awaitTerminationSeconds > 0) {
            try {
                if (!this.executor.awaitTermination(this.awaitTerminationSeconds, TimeUnit.SECONDS)) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Timed out while waiting for executor" + " to terminate");
                    }
                }
            }catch (InterruptedException ex) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Interrupted while waiting for executor" + " to terminate");
                }
                Thread.currentThread().interrupt();
            }
        }
    }
}
