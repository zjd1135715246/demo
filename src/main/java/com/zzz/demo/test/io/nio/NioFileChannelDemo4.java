package com.zzz.demo.test.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author zjd
 * @Date 2021/1/6 16:34
 */
public class NioFileChannelDemo4 {

    public static void main(String[] args) throws Exception{

        FileInputStream inputStream = new FileInputStream("D:\\work\\space\\zz\\other\\7.jpg");
        FileChannel inputStreamChannel = inputStream.getChannel();

        FileOutputStream outputStream = new FileOutputStream("D:\\work\\space\\zz\\other\\7a.jpg");
        FileChannel outputStreamChannel = outputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());

        inputStream.close();
        outputStream.close();
    }
}
