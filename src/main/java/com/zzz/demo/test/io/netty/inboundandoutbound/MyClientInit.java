package com.zzz.demo.test.io.netty.inboundandoutbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author zjd
 * @Date 2021/1/16 15:06
 */
public class MyClientInit extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast(new MyEncode());
        pipeline.addLast(new MyDecode());

        pipeline.addLast(new MyClientHandler());
    }
}
