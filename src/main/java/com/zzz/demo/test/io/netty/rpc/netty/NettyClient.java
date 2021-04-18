package com.zzz.demo.test.io.netty.rpc.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zjd
 * @Date 2021/1/17 15:52
 */
public class NettyClient {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static NettyClientHandler client;

    public Object getBean(final Class<?> serviceClass,final String provider){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),new Class<?>[] {serviceClass},((proxy, method, args) -> {
            if(client == null){
                initClient();
            }
            client.setParam(provider+args[0]);
            return executor.submit(client).get();
        }));

    }

    public static void initClient(){
        client = new NettyClientHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY,true).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new StringEncoder());
                    pipeline.addLast(new StringDecoder());
                    pipeline.addLast(client);
                }
            });
            bootstrap.connect("127.0.0.1", 8000).sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

}
