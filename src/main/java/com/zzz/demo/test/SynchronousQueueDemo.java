package com.zzz.demo.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String> queue = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"放入 a");
                queue.put("a");
                System.out.println(Thread.currentThread().getName()+"放入 b");
                queue.put("b");
                System.out.println(Thread.currentThread().getName()+"放入 c");
                queue.put("c");
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();


        new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"取 "+queue.take());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"取 "+queue.take());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+"取 "+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();
    }
}
