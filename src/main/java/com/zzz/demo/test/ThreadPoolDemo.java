package com.zzz.demo.test;

import sun.plugin.security.StripClassFile;

import java.util.concurrent.*;

/**
 * @author zjd
 * @date 2020/12/30
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

        //jdk
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //ExecutorService threadPool = Executors.newCachedThreadPool();

        //自定义
        ExecutorService threadPool = new ThreadPoolExecutor(2,5,1L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"处理");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }



    }
}
