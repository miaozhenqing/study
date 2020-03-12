package com.example.qzm.study.function.http.asyncHttp2;

import com.alibaba.fastjson.JSONObject;
import io.netty.channel.nio.NioEventLoopGroup;
import org.asynchttpclient.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;

/**
 * @ClassName HttpTestClient
 * @Description TODO
 * @Version 1.0
 **/
public class HttpTestClient {
    private static final Logger logger = LoggerFactory.getLogger(HttpTestClient.class);

    public static void main(String[] args) {
        doInit2();
    }
    private static void doInit2() {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(new DefaultAsyncHttpClientConfig
                .Builder()
                .setMaxConnections(100)
                .setKeepAlive(true)
                .setRequestTimeout(3000)
                .build());
        BoundRequestBuilder boundRequestBuilder = asyncHttpClient
                .preparePost("http://192.168.0.183:10001/http/postTest")
                .setHeader(CONTENT_TYPE, APPLICATION_JSON)
                .setRequestTimeout(3000)
                .setReadTimeout(3000)
                .setBody(reqBody());
        asyncHttpClient.executeRequest(boundRequestBuilder.build(), new AsyncCompletionHandlerBase() {
            @Override
            public Response onCompleted(Response response) throws Exception {
                int code = response.getStatusCode();
                String responseBody = response.getResponseBody();

                logger.debug("onCompleted...code={},responseBody={}", code, responseBody);
                return response;
            }
            @Override
            public void onThrowable(Throwable throwable) {
                logger.debug("onThrowable...throwable={}", throwable.getMessage());
            }
        });
    }

    private static void doInit1() {
        AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient(new DefaultAsyncHttpClientConfig.Builder().build());
        BoundRequestBuilder boundRequestBuilder = asyncHttpClient
                .preparePost("http://192.168.0.183:10001/http/postTest")
                .setHeader(CONTENT_TYPE, APPLICATION_JSON)
                .setRequestTimeout(3000)
                .setReadTimeout(3000)
                .setBody(reqBody());
        ListenableFuture<Response> listenableFuture = boundRequestBuilder.execute();
        //1
        listenableFuture.addListener(()->{
            logger.debug("addListener...threadName={}", Thread.currentThread().getName());
        }, ((DefaultAsyncHttpClient) asyncHttpClient).getEventLoopGroup());
        //2
        CompletableFuture<Response> completableFuture = listenableFuture.toCompletableFuture();
        completableFuture.whenComplete(((response, throwable) -> {
            int code = response.getStatusCode();
            String message = response.getResponseBody();
            logger.debug("whenComplete...code={},message={},threadName={}", code, message, Thread.currentThread().getName());
        }));
        completableFuture.whenCompleteAsync(((response, throwable) -> {
            int code = response.getStatusCode();
            String message = response.getResponseBody();
            logger.debug("whenCompleteAsync...code={},message={},threadName={}", code, message, Thread.currentThread().getName());
        }));
    }

    private static String reqBody() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "tooom");
        map.put("age", "1");
        return JSONObject.toJSONString(map);
    }


}
