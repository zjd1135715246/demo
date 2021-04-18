package com.zzz.demo.test.io.netty.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * @author zjd
 * @Date 2021/1/14 16:39
 */
public class ChatClient {

    private  final String host;
    private  final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run(){

        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(loopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast("decoder", new StringDecoder());
                    pipeline.addLast("encoder", new StringEncoder());
                    pipeline.addLast(new ChatClientHandler());
                }
            });

            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("----------"+channel.localAddress()+"-----------------");

            Scanner sc = new Scanner(System.in);
            while (true){
                if(sc.hasNext()){
                    String s = sc.nextLine();
                    channel.writeAndFlush(s);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            loopGroup.shutdownGracefully();
        }



    }

    public static void main(String[] args) {
        new ChatClient("127.0.0.1",8000).run();
    }
}
