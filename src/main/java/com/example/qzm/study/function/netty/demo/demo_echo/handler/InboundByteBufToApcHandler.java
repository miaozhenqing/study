package com.example.qzm.study.function.netty.demo.demo_echo.handler;

import com.alibaba.fastjson.JSONObject;
import com.example.qzm.study.function.netty.demo.demo_echo.apc.Apc;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <pre>
 *      +------------------------------+------------------------+-----------------------+-----------------------------+-------------------+
 *      | msgLength(4字节 消息字节数)   | msgId(4字节-消息标识)   |  crc(8字节-包完整性)   |  incode(4字节-消息有序性值)  |  apc 消息结构体   |
 *      +------------------------------+------------------------+-----------------------+-----------------------------+-------------------+
 *      |----------------------------------------------------包描述---------------------------------------------------|---apc 消息结构体--|
 * </pre>
 **/
@Slf4j
public class InboundByteBufToApcHandler extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext context, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.markReaderIndex();
        if (!byteBuf.isReadable(4)) {
            byteBuf.resetReaderIndex();
            log.warn("warning...isReadable less then 4");
            return;
        }
        int msgLength = byteBuf.readInt();
        if (byteBuf.readableBytes() < msgLength) {
            byteBuf.resetReaderIndex();
            log.warn("warning...data is lack");
            return;
        }
        int msgId = byteBuf.readInt();
        long crc = byteBuf.readLong();
        int code = byteBuf.readInt();
        byte[] bytes = new byte[msgLength - 4 - 8 - 4];
        byteBuf.readBytes(bytes);
        String apcStr = new String(bytes);
        System.out.println(apcStr);
        log.debug("InboundByteBufToApcHandler...msgLength={},msgId={},crc={},code={}", msgLength, msgId, crc, code);
    }

}
