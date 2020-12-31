package com.zzz.demo.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7,()->{ System.out.println("收集完，召唤");});

        for (int i = 1; i < 8; i++) {
            final int fI = i;
            new Thread(()->{
                System.out.println("收集到"+fI+"星龙珠");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }




    }
}
