package com.zzz.demo.test.io.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author zjd
 * @Date 2021/1/14 10:50
 */
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //添加一个http编码解码器
        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());

        pipeline.addLast("MyServerHandler",new ServerHandler());
    }
}
