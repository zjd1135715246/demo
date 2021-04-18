package com.zzz.demo.test.io.netty.rpc.provider;

import com.zzz.demo.test.io.netty.rpc.netty.NettyServer;

/**
 * @author zjd
 * @Date 2021/1/17 15:51
 */
public class Server {
    public static void main(String[] args) {
        NettyServer.nettyServer("127.0.0.1",8000);
    }
}
