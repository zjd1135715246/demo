package com.zzz.demo.test.io.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author zjd
 * @Date 2021/1/8 11:35
 */
public class ChatClient {

    private Selector selector;
    private SocketChannel socketChannel;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 6666;

    public ChatClient(){
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST,PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            System.out.println(socketChannel.getLocalAddress()+" is ok");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void send(String msg){
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void receive() {
        try {
            int select = selector.select(2000);
            if (select > 0) {
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
//                             ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
//                             System.out.println("from :"+channel.getLocalAddress()+"msg:"+ new String(byteBuffer.array()));
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        System.out.println(channel.getClass());
                        channel.read(byteBuffer);
                        System.out.println("from :" + channel.getRemoteAddress() + "msg:" + new String(byteBuffer.array()));
                    }
                    keyIterator.remove();
                }
            }else {
                //System.out.println("没有可以用的通道");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient();
        new Thread(()->{
            while (true){
                client.receive();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (true){
            try {
                Scanner scanner = new Scanner(System.in);
                String str = scanner.next();
                client.send(str);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

}
