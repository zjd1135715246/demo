package com.zzz.demo.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author zjd
 * @date 2020/12/29
 */
public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);


        blockingQueue.add("a");
        blockingQueue.element();
        blockingQueue.remove("a");

        blockingQueue.offer("a");
        blockingQueue.peek();
        blockingQueue.poll();

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("d");

        blockingQueue.take();

        blockingQueue.offer("a",2L, TimeUnit.SECONDS);
    }
}
