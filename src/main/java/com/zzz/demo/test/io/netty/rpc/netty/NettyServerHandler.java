package com.zzz.demo.test.io.netty.rpc.netty;

import com.zzz.demo.test.io.netty.rpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

/**
 * @author zjd
 * @Date 2021/1/17 15:40
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"连接");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到消息："+msg);
        String s = new HelloServiceImpl().Hello(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
        System.out.println(s);
        ctx.writeAndFlush(s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
