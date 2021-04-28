package com.zzz.demo.test.io.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zjd
 * @Date 2021/1/7 16:17
 */
public class NioClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();

        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 6666);
        socketChannel.connect(socketAddress);
        String str = "hello zzz";

        socketChannel.write(ByteBuffer.wrap(str.getBytes()));
        System.in.read();
    }
}
