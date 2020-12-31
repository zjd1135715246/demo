package com.zzz.demo.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zjd
 * @date 2020/12/30
 */
public class CallableDemo {

    public static void main(String[] args) throws Exception {

        FutureTask<Integer> task = new FutureTask<>(new MyThread());
        new Thread(task,"AA").start();
        Integer res = task.get();
        System.out.println(res+100);
    }

}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" come");
        return 1024;
    }
}
