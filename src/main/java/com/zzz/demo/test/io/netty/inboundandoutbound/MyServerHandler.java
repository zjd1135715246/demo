package com.zzz.demo.test.io.netty.inboundandoutbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zjd
 * @Date 2021/1/16 15:02
 */
public class MyServerHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Long aLong) throws Exception {
        System.out.println("客户端发送数据："+aLong);
        ctx.writeAndFlush(999L);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
