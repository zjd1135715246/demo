package com.zzz.demo.test.io.netty.fst;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author zjd
 * @Date 2021/1/11 15:31
 */
public class NettyServer {
    public static void main(String[] args) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)                    //设置两个线程组
                    .channel(NioServerSocketChannel.class)          //使用NioServerSocketChannel，作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG,128)   //设置线程队列得到的连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true)        //设置保持活动连接状态
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });

            System.out.println("服务器 is ready ............");

            //绑定一个端口并同步，生成一个ChannelFuture
            ChannelFuture channelFuture = serverBootstrap.bind(6000).sync();
            //对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }




    }
}
