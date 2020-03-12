package com.example.qzm.study.function.netty.demo.demo_echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName ServerInHandler
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
public class InboundReadBytesHandler extends SimpleChannelInboundHandler<byte[]> {
    @Override
    protected void channelRead0(ChannelHandlerContext cxt, byte[] bytes) throws Exception {
    }
}
