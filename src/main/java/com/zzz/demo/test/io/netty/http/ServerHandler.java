package com.zzz.demo.test.io.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * @author zjd
 * @Date 2021/1/14 10:50
 */
public class ServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    /**
     * 读取客户端传入的数据
     * @param ctx
     * @param msg            用于和客户端通信
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        if(msg instanceof HttpRequest){
            System.out.println("msg 类型："+msg.getClass());
            System.out.println("客户端地址："+ctx.channel().remoteAddress());

            HttpRequest httpRequest = (HttpRequest)msg;

            URI uri = new URI(httpRequest.uri());

            if("/favicon.ico".equals(uri.getPath())){
                System.out.println("不做处理");
                return;
            }

            //回复客户端，需要http协议
            ByteBuf content = Unpooled.copiedBuffer("hello 我是服务器", CharsetUtil.UTF_8);
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());
            ctx.writeAndFlush(response);
        }

    }
}
