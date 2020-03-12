package com.example.qzm.study.function.netty.demo.demo_echo.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import sun.nio.cs.UTF_32;

import java.util.List;

import static io.netty.util.CharsetUtil.UTF_8;

/**
 * @ClassName InBoundByteBufToStringHandler
 * @Description TODO
 * @Version 1.0
 **/
@Slf4j
public class InboundByteBufToStringHandler extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf byteBuf, List<Object> list) throws Exception {
        String result = byteBuf.toString(CharsetUtil.UTF_8);
        list.add(result);
    }
}
