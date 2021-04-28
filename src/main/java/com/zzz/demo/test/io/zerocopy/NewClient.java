package com.zzz.demo.test.io.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zjd
 * @Date 2021/1/11 10:37
 */
public class NewClient {
    public static void main(String[] args) throws Exception{

        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1",7000));

        String fileName = "D:\\work\\space\\zz\\other\\Docker.html";


        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long start = System.currentTimeMillis();

        long count = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("传递了："+count+"耗时："+(System.currentTimeMillis()-start));

        fileChannel.close();
    }
}
