package com.zzz.demo.test.io.zerocopy;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zjd
 * @Date 2021/1/11 10:31
 */
public class NewServer {
    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(new InetSocketAddress(7000));


        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println("连接上....");
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            int read = 0 ;
            while (read!=-1){
                read = socketChannel.read(byteBuffer);
                byteBuffer.rewind();
            }


        }

    }
}
