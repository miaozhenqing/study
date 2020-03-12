package com.example.qzm.study.function.netty.demo.demo_echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @ClassName ServerInHandler
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
public class InboundReadStringHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext context, String s) throws Exception {
        System.out.println(s+"\n");
    }
}
