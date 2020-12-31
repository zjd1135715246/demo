package com.zzz.demo.test;

import java.util.concurrent.CountDownLatch;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            final int fI = i;
            new Thread(()->{
                System.out.println("第"+fI+"个");
                latch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("统一");


    }
}
