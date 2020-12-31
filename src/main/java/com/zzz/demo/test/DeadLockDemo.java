package com.zzz.demo.test;

/**
 * @author zjd
 * @date 2020/12/30
 */
public class DeadLockDemo {

    /**
     * jps java里类似linux下的 ps 命令
     *      jps -l
     *  jstack  查看栈信息
     */

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLock(lockA,lockB),"AA").start();
        new Thread(new DeadLock(lockB,lockA),"BB").start();

    }

}

class DeadLock implements Runnable{

    private String lockA;
    private String lockB;

    public DeadLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"持有锁A，尝试获取"+lockB);
            synchronized (lockB){
                System.out.println("获取成功");
            }
        }
    }
}
