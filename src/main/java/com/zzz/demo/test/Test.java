package com.zzz.demo.test;

import com.zzz.demo.entity.Address;
import lombok.Data;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangjiadong
 * @date 2020/9/25
 */
public class Test {

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();
        while (true){
                if(threadDemo.isFlag()){
                    System.out.println("threadDemo flag 修改------为true");
                    break;
                }


        }
        int[] nums = new int[]{1,5,8,7,6,9};
        int[] ints = twoSum(nums, 10);
        System.out.println(ints[0]+":"+ints[1]);
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];

        // 建立k-v ，一一对应的哈希表
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target-nums[i],i);
        }
        return indexs;
    }

    }

@Data
class ThreadDemo implements Runnable{
    private volatile boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        }catch (Exception e){
        }
        flag = true;
        System.out.println("threadDemo flag 修改为："+flag);
    }
}


class AtomicDemo implements Runnable{

    private int i =0;
    private final AtomicInteger atomicInteger = new AtomicInteger();
    private int getI(){
        return atomicInteger.getAndIncrement();
    }
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        }catch (Exception e){
        }
        System.out.println(getI());
    }
}



