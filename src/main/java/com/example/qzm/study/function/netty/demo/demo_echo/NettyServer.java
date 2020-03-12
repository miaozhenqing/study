package com.example.qzm.study.function.netty.demo.demo_echo;

import com.example.qzm.study.function.netty.demo.demo_echo.handler.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName NettyServer
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
public class NettyServer {
    public final static AttributeKey<String> ABS_TEST_ATTR = AttributeKey.newInstance("ABS_TEST_ATTR");
    public final static AttributeKey<String> TEST_ATTR = AttributeKey.newInstance("TEST_ATTR");

    public static void main(String[] args) throws Exception {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup(16);
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .attr(ABS_TEST_ATTR, "ABS_TEST_ATTR")
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel nioServerSocketChannel) throws Exception {
                        //handler()用于指定在服务端启动过程中的一些逻辑
                        log.debug("handler...initChannel,threadName={}", Thread.currentThread().getName());
                    }
                })
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childAttr(TEST_ATTR, "TEST_ATTR")
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    //childHandler()用于指定处理新连接数据的读写处理逻辑
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
//                        ch.pipeline().addLast(new InboundByteBufToStringHandler());
//                        ch.pipeline().addLast(new InboundReadStringHandler());
//                        ch.pipeline().addLast(new InboundReadBytesHandler());
                        ch.pipeline().addLast(new InboundByteBufToApcHandler());
                        ch.pipeline().addLast(new InboundReadApcHandler());
                    }
                })
                .bind(8000).addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            log.debug("bind port success，port=8000");
                        } else {
                            log.error("bind port fail，port=8000");
                        }
                    }
                }).sync();
    }
}
