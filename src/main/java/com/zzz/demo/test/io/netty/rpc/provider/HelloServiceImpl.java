package com.zzz.demo.test.io.netty.rpc.provider;

/**
 * @author zjd
 * @Date 2021/1/17 15:33
 */
public class HelloServiceImpl implements HelloService{

    @Override
    public String Hello(String msg) {
        if(msg == null){
            System.out.println("客户端，我已收到你的消息");
        }
        System.out.println("客户端，我已收到你的消息 ："+msg);
        return "ok";
    }
}
