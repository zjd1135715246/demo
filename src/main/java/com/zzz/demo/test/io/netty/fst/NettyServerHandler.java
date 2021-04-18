package com.zzz.demo.test.io.netty.fst;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author zjd
 * @Date 2021/1/11 15:46
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 接受消息
     * @param ctx   上下文
     * @param msg   消息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //加入异步队列
        ctx.channel().eventLoop().execute(()->{
            try {
                 Thread.sleep(2000);
                System.out.println("异步队列线程："+Thread.currentThread().getName());
                ctx.writeAndFlush(Unpooled.copiedBuffer("task",CharsetUtil.UTF_8));
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        System.out.println("ctx server :"+ctx);
        ByteBuf buf = (ByteBuf) msg;
        System.out.println("客户端发送的消息为："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址："+ctx.channel().remoteAddress());
    }


    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello 客户端",CharsetUtil.UTF_8));
    }

    /**
     * 处理异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
