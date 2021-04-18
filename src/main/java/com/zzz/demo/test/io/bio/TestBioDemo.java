package com.zzz.demo.test.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zjd
 * @Date 2021/1/6 14:29
 */
public class TestBioDemo {

    public static void main(String[] args) throws Exception{

        ExecutorService threadPool = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        while (true){
            System.out.println("线程信息 id="+Thread.currentThread().getId() + "线程名 = "+Thread.currentThread().getName());
            System.out.println("等待连接........");
            final Socket socket = serverSocket.accept();
            threadPool.execute(()->{
                handler(socket);
            });

        }

    }

    public static  void  handler(Socket socket){
        byte[] bytes = new byte[1024];
            try {
                System.out.println("线程信息 id="+Thread.currentThread().getId() + "线程名 = "+Thread.currentThread().getName());
                InputStream inputStream = socket.getInputStream();
                System.out.println("等待输入.......");
                while (true){
                    int read = inputStream.read(bytes);
                    if(read!=-1){
                        System.out.println(new String(bytes,0,read));
                    }else {
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

    }
}
