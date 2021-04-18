package com.zzz.demo.test.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zjd
 * @Date 2021/1/6 16:25
 */
public class NioFileChannelDemo3 {

    public static void main(String[] args) throws Exception{

        FileInputStream inputStream = new FileInputStream("D:\\work\\space\\zz\\other\\1.txt");
        FileChannel inputStreamChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("D:\\work\\space\\zz\\other\\2.txt");
        FileChannel outputStreamChannel = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (true){
            byteBuffer.clear();
            int read = inputStreamChannel.read(byteBuffer);
            if (read==-1){
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }

        inputStream.close();
        outputStream.close();
    }
}
