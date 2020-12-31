package com.zzz.demo.test;

import lombok.Data;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class P_CDemo {

    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(()->{
            try {
                for (int i = 1; i < 6; i++) {
                    shareData.increment();
                    System.out.println(Thread.currentThread().getName()+"当前："+shareData.getNum());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                for (int i = 1; i < 6; i++) {
                    shareData.decrement();
                    System.out.println(Thread.currentThread().getName() + "当前：" + shareData.getNum());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();

        new Thread(()->{
            try {
                for (int i = 1; i < 6; i++) {
                    shareData.increment();
                    System.out.println(Thread.currentThread().getName()+"当前："+shareData.getNum());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"AA").start();

        new Thread(() -> {
            try {
                for (int i = 1; i < 6; i++) {
                    shareData.decrement();
                    System.out.println(Thread.currentThread().getName() + "当前：" + shareData.getNum());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();

    }
}
@Data
class ShareData {
    private int num =  0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment(){
        try {
            lock.lock();
            while (num!=0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num++;
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        try {
            lock.lock();
            while (num==0){
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            num--;
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

}
