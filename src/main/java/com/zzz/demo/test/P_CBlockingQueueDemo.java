package com.zzz.demo.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class P_CBlockingQueueDemo {

    public static void main(String[] args) {
        ShareDemo3 shareDemo3 = new ShareDemo3(new ArrayBlockingQueue<String>(5));

        new Thread(shareDemo3::prod,"AA").start();

        new Thread(shareDemo3::custom,"AA").start();

        try {
            Thread.sleep(3000);
            shareDemo3.stop();
        }catch (Exception e){

        }
        System.out.println("时间到");
    }

}

class ShareDemo3{
    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> queue = null;

    public ShareDemo3(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void prod(){
        while (flag){
            String data = atomicInteger.getAndIncrement() + "";
            try {
                queue.offer(data,2L, TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+"添加"+data);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(!flag){
            System.out.println("停，prod停止");
        }
    }

    public void custom(){
        while (flag){
            try {
                String data = queue.poll(2L,TimeUnit.SECONDS);
                System.out.println(Thread.currentThread().getName()+"取出"+data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!flag){
            System.out.println("停，custom停止");
        }
    }

    public void stop(){
        this.flag = false;
    }

}
