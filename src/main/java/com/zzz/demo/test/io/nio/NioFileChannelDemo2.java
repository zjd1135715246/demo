package com.zzz.demo.test.io.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zjd
 * @Date 2021/1/6 16:14
 */
public class NioFileChannelDemo2 {

    public static void main(String[] args) throws Exception{

        FileInputStream inputStream = new FileInputStream("D:\\work\\space\\zz\\other\\1.txt");

        FileChannel fileChannel = inputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        fileChannel.read(byteBuffer);

        System.out.println(new String(byteBuffer.array()));

        inputStream.close();

    }
}
