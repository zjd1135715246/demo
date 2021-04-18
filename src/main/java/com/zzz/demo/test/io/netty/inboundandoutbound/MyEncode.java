package com.zzz.demo.test.io.netty.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zjd
 * @Date 2021/1/16 15:07
 */
public class MyEncode extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Long msg, ByteBuf byteBuf) throws Exception {
        System.out.println("MyClientEncode encode被调用");
        byteBuf.writeLong(msg);
    }
}
