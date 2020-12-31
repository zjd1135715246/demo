package com.zzz.demo.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class SyncLockDemo {

    public static void main(String[] args) {

        ShareData2 data = new ShareData2();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                data.print5();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                data.print10();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                data.print15();
            }
        },"CC").start();
    }
}

class ShareData2{
    private int num = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5(){
        lock.lock();
        try {
            while (num!=1){
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"---"+i);
            }
            num = 2;
            c2.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try {
            while (num!=2){
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"---"+i);
            }
            num = 3;
            c3.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try {
            while (num!=3){
                try {
                    c3.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"---"+i);
            }
            num = 1;
            c1.signal();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
