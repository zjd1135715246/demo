package com.zzz.demo.test.io.nio;

import java.nio.Buffer;
import java.nio.IntBuffer;

/**
 * @author zjd
 * @Date 2021/1/6 15:09
 */
public class Demo {

    public static void main(String[] args) throws Exception{

        IntBuffer intBuffer = IntBuffer.allocate(6);

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }

        //切换buffer模式,读写切换
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

        System.out.println("----------------");
        IntBuffer readOnlyBuffer = intBuffer.asReadOnlyBuffer();
        readOnlyBuffer.clear();
        System.out.println(readOnlyBuffer.getClass());
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }

    }
}
