package com.zzz.demo.test.io.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zjd
 * @Date 2021/1/6 16:02
 */
public class NioFileChannelDemo1 {

    public static void main(String[] args) throws Exception{

        String str = "hello zzz";

        FileOutputStream outputStream = new FileOutputStream("D:\\work\\space\\zz\\other\\1.txt");

        FileChannel fileChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(str.getBytes());

        byteBuffer.flip();

        fileChannel.write(byteBuffer);

        outputStream.close();
    }
}
