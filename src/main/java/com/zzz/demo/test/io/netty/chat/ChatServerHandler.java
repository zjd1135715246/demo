package com.zzz.demo.test.io.netty.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author zjd
 * @Date 2021/1/14 16:31
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    //GlobalEventExecutor.INSTANCE 全局的事件处理器
    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    //表示连接建立，一旦连接，第一个被执行
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush(channel.remoteAddress()+"加入了群聊");
        channels.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channels.writeAndFlush(channel.remoteAddress()+"离开了群聊");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ctx.writeAndFlush("[客户]"+channel.remoteAddress()+"上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        ctx.writeAndFlush("[客户]"+channel.remoteAddress()+"下线了");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        Channel channel = ctx.channel();
        channels.forEach(ch->{
            if(ch != channel){
                ch.writeAndFlush("[客户]"+channel.remoteAddress()+"发送："+s);
            }
        });
    }
}
