package com.zzz.demo.test.io.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zjd
 * @Date 2021/1/7 14:29
 */
public class MappedByteBufferDemo {
    public static void main(String[] args) throws Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\work\\space\\zz\\other\\1.txt","rw");

        FileChannel fileChannel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);

        mappedByteBuffer.put(0,(byte) 'Z');

        randomAccessFile.close();
    }
}
