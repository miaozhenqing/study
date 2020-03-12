package com.example.qzm.study.function.netty.demo.demo_echo;

import com.example.qzm.study.function.netty.demo.demo_echo.apc.Apc;
import com.example.qzm.study.function.netty.demo.demo_echo.handler.OutboundApcToByteBufHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Scanner;

/**
 * @ClassName NettyClient
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
public class NettyClient {
    private static Channel channel;

    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new OutboundApcToByteBufHandler());
                        channel = ch;
                    }
                });

        bootstrap.connect("127.0.0.1", 8000).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    sendMessage();
                    log.debug("NettyClient...connect success");
                } else {
                    log.error("NettyClient...connect fail");
                }
            }
        });
    }

    public static void sendMessage() {
        System.out.println("input:\n");
        int num = 1;
        while ((num--) > 0) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            channel.writeAndFlush(new Apc("function" + num, "parameter" + num));
        }
    }
}
