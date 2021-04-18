package com.zzz.demo.test.io.netty.rpc.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.Callable;

/**
 * @author zjd
 * @Date 2021/1/17 15:52
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable<Object> {


    private ChannelHandlerContext context;
    private String result;
    private String param;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        context = ctx;
    }

    @Override
    public synchronized void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg.toString());
        result = msg.toString();
        notify();
    }

    @Override
    public synchronized Object call() throws Exception {
        context.writeAndFlush(param);
        System.out.println("12345");
        wait();
        return result;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void setParam(String param) {
        this.param = param;
    }
}
