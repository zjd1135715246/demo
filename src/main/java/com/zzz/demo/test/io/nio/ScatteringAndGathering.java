package com.zzz.demo.test.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author zjd
 * @Date 2021/1/7 14:53
 */
public class ScatteringAndGathering {
    public static void main(String[] args) throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(7000));

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        SocketChannel socketChannel = serverSocketChannel.accept();
        while (true){
            long readLength = 0;
            while (readLength<8){
                long l = socketChannel.read(byteBuffers);
                Arrays.stream(byteBuffers).map(byteBuffer -> "position=:"+byteBuffer.position() + "limit = "+byteBuffer.limit()).forEach(System.out::println);
                readLength+=l;
            }
            Arrays.stream(byteBuffers).forEach(ByteBuffer::flip);
            long writeLength = 0;

            while (writeLength<8){
                long l = socketChannel.write(byteBuffers);
                writeLength+=l;
            }

            Arrays.stream(byteBuffers).forEach(ByteBuffer::clear);

            System.out.println("readLength:"+readLength + "writeLength:"+writeLength);



        }

    }
}
