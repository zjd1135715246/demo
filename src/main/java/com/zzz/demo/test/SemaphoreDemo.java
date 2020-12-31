package com.zzz.demo.test;

import java.util.concurrent.Semaphore;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i < 7; i++) {
            final int fI = i;
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(fI+"号车占用一个车位");
                    Thread.sleep(3000);
                    System.out.println(fI+"号车离开");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }



    }
}
