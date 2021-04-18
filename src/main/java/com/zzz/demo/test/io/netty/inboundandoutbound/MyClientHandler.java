package com.zzz.demo.test.io.netty.inboundandoutbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author zjd
 * @Date 2021/1/16 15:09
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {
        System.out.println("服务器回复数据："+aLong);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler 发送数据");
        ctx.writeAndFlush(12345678L);
        //ctx.writeAndFlush(Unpooled.copiedBuffer("abcdefgh", CharsetUtil.UTF_8));
    }
}
