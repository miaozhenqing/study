package com.example.qzm.study.function.netty.demo.demo_echo.handler;

import com.example.qzm.study.function.netty.demo.demo_echo.apc.Apc;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @ClassName InBoundByteBufToStringHandler
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
public class InboundReadApcHandler extends MessageToMessageDecoder<Apc> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Apc apc, List<Object> list) throws Exception {
        System.out.println(apc.toJson());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }
}
