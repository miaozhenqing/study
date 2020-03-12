package com.example.qzm.study.function.http.asynchttp1.example;

import org.asynchttpclient.AsyncCompletionHandlerBase;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.BiConsumer;

import static org.asynchttpclient.Dsl.asyncHttpClient;
import static org.asynchttpclient.Dsl.config;

/**
 * Created by Stone Mack on 2018/12/6.
 */
public class HttpReportService {
    private static final Logger logger = LoggerFactory.getLogger(HttpReportService.class);
    // 上报事件执行线程池
    private final ScheduledExecutorService executorService;
    // 上报客户端
    private final AsyncHttpClient client;
    // 上报超时
    private final int requestTimeOut;
    // 丢弃策略
    final RejectedExecutionHandler defaultRejectHandler = (runnable, executor) -> this.discardCheckRunnable(executor, runnable);

    private HttpReportService(ScheduledExecutorService executorService, RejectedExecutionHandler rejectedExecutionHandler, int reportThreadNum, int requestTimeOut) {
        final RejectedExecutionHandler rejectedHandler = rejectedExecutionHandler == null ? defaultRejectHandler : rejectedExecutionHandler;
        if (executorService == null) {
            this.executorService = new ScheduledThreadPoolExecutor(reportThreadNum, rejectedHandler);
        } else {
            this.executorService = executorService;
        }
        this.requestTimeOut = requestTimeOut;
        this.client = buildAsyncHttpClient();
    }

    private AsyncHttpClient buildAsyncHttpClient() {
        return asyncHttpClient(config().setKeepAlive(true).setMaxConnections(8).setRequestTimeout(this.requestTimeOut));
    }

    /**
     * 消息丢弃处理
     */
    private void discardCheckRunnable(final ThreadPoolExecutor executor, final Runnable runnable) {
        // 消息丢弃
        logger.warn("discard message executor={},verifyRunnable={}", executor, runnable);
    }

    public static class Builder {
        // 上报事件执行线程池
        private ScheduledExecutorService executorService;
        // 丢弃策略
        private RejectedExecutionHandler rejectedExecutionHandler;
        // 上报业务线程
        private int reportThreadNum = 2;
        // 上报超时
        private int requestTimeOut = 1500;

        Builder() {
        }

        public HttpReportService build() {
            return new HttpReportService(executorService, rejectedExecutionHandler, reportThreadNum, requestTimeOut);
        }

        public Builder setExecutorService(ScheduledExecutorService executorService) {
            this.executorService = executorService;
            return this;
        }

        public Builder setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
            this.rejectedExecutionHandler = rejectedExecutionHandler;
            return this;
        }

        public Builder setReportThreadNum(int reportThreadNum) {
            this.reportThreadNum = reportThreadNum;
            return this;
        }

        public Builder setRequestTimeOut(int requestTimeOut) {
            this.requestTimeOut = requestTimeOut;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public void reportRunnable(final Runnable runnable) {
        executorService.submit(runnable);
    }

    /**
     * 上报数据
     *
     * @param reportUrl  上报数据的url
     * @param reportData 上报的字符串
     */
    public void report(final String reportUrl, final String reportData) {
        final DefaultReportRunnable runnable = new DefaultReportRunnable(reportUrl, reportData);
        executorService.submit(runnable);
    }

    /**
     * 上报数据
     *
     * @param reportUrl 上报数据的url
     * @param postData  上报的字符串
     */
    public void report(final String reportUrl, final Map<String, String> postData) {
        final DefaultReportRunnableOfMap runnable = new DefaultReportRunnableOfMap(reportUrl, postData);
        executorService.submit(runnable);
    }

    /**
     * 上报数据,返回http结果
     *
     * @param reportUrl 上报数据的url
     * @param postData  上报的字符串
     */
    public void report(final String reportUrl, final Map<String, String> postData, final BiConsumer<Integer, String> consumer) {
        final DefaultReportRunnableOfMap runnable = new DefaultReportRunnableOfMap(reportUrl, postData, consumer);
        executorService.submit(runnable);
    }

    /**
     * 默认的上报处理业务
     */
    class DefaultReportRunnable implements Runnable {
        private final String reportUrl;
        private final String reportData;

        public DefaultReportRunnable(String reportUrl, String reportData) {
            this.reportUrl = reportUrl;
            this.reportData = reportData;
        }

        @Override
        public void run() {
            final BoundRequestBuilder requestBuilder = client.preparePost(reportUrl).setBody(reportData);
            final long startTime = System.nanoTime();
            client.executeRequest(requestBuilder.build(), new AsyncCompletionHandlerBase() {
                @Override
                public Response onCompleted(Response response) {
                    // 响应码
                    final int statusCode = response.getStatusCode();
                    String respBody = response.getResponseBody();
                    long endTime = System.nanoTime();
                    logger.debug("lostTime={}, statusCode={}, reportUrl={}", (endTime - startTime) / 1000000f, statusCode, reportUrl);
                    return response;
                }
            });
        }
    }

    /**
     * 默认的上报处理业务
     */
    class DefaultReportRunnableOfMap implements Runnable {
        private final String reportUrl;
        private final Map<String, String> postData;
        private final BiConsumer<Integer, String> consumer;

        public DefaultReportRunnableOfMap(String reportUrl, final Map<String, String> postData) {
            this.reportUrl = reportUrl;
            this.postData = postData;
            this.consumer = null;
        }

        public DefaultReportRunnableOfMap(String reportUrl, final Map<String, String> postData, final BiConsumer<Integer, String> consumer) {
            this.reportUrl = reportUrl;
            this.postData = postData;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            final BoundRequestBuilder requestBuilder = client.preparePost(reportUrl);
            for (Map.Entry<String, String> entry : postData.entrySet()) {
                requestBuilder.addFormParam(entry.getKey(), String.valueOf(entry.getValue()));
            }
            final long startTime = System.nanoTime();
            client.executeRequest(requestBuilder.build(), new AsyncCompletionHandlerBase() {
                @Override
                public Response onCompleted(Response response) {
                    // 响应码
                    final int statusCode = response.getStatusCode();
                    String respBody = response.getResponseBody();
                    long endTime = System.nanoTime();
                    logger.debug("lostTime={}, statusCode={}, reportUrl={} ,responseBody={}", (endTime - startTime) / 1000000f, statusCode, reportUrl, respBody);
                    //记录不成功数据
                    if (consumer != null) {
                        consumer.accept(statusCode, respBody);
                    }
                    return response;
                }
                @Override
                public void onThrowable(Throwable t) {
                    String msg = t.getMessage();
                    if (consumer != null) {
                        consumer.accept(500, msg);
                    }
                    logger.error(t.getMessage(), t);
                }
            });
        }
    }
}
