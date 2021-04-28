package com.zzz.demo.test.io.netty.rpc.customer;

import com.zzz.demo.test.io.netty.rpc.netty.NettyClient;
import com.zzz.demo.test.io.netty.rpc.provider.HelloService;

/**
 * @author zjd
 * @Date 2021/1/17 15:34
 */
public class Client {

    private final static String providerName = "HelloService#Hello#";

    public static void main(String[] args) {
        NettyClient client = new NettyClient();
        HelloService helloService = (HelloService) client.getBean(HelloService.class,providerName);

        String msg = helloService.Hello("你好");
        System.out.println("服务器返回结果:"+msg);
    }
}
