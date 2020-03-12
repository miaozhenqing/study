package com.example.qzm.study.function.netty.demo.demo_echo.handler;

import com.example.qzm.study.function.netty.demo.demo_echo.apc.Apc;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *      +------------------------------+------------------------+-----------------------+-----------------------------+-------------------+
 *      | msgLength(4字节 消息字节数)   | msgId(4字节-消息标识)   |  crc(8字节-包完整性)   |  incode(4字节-消息有序性值)  |  apc 消息结构体   |
 *      +------------------------------+------------------------+-----------------------+-----------------------------+-------------------+
 *      |----------------------------------------------------包描述---------------------------------------------------|---apc 消息结构体--|
 * </pre>
 **/
@Slf4j
public class OutboundApcToByteBufHandler extends MessageToByteEncoder<Apc> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Apc apc, ByteBuf byteBuf) throws Exception {
        byte[] bytes = apc.toJson().getBytes();
        int bytesLength = bytes.length;
        int msgLength = 4 + 8 + 4 + bytesLength;
        int msgId = 123;
        long crc = 456;
        int code = 789;
        byteBuf.writeInt(msgLength);
        byteBuf.writeInt(msgId);
        byteBuf.writeLong(crc);
        byteBuf.writeInt(code);
        byteBuf.writeBytes(bytes);
        log.debug("OutboundApcToByteBufHandler...msgLength={},bytesLength={},msgId={},crc={},code={},apc={}"
                , msgLength, bytesLength, msgId, crc, code, apc.toJson());
    }
}
