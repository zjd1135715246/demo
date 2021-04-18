package com.zzz.demo.test.io.nio.chat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zjd
 * @Date 2021/1/8 11:18
 */
public class ChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6666;

    public ChatServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true){
            try {
                int select = selector.select();
                if (select > 0) {
                    Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                    while (keyIterator.hasNext()) {
                        SelectionKey selectionKey = keyIterator.next();
                        if (selectionKey.isAcceptable()) {
                            SocketChannel channel = serverSocketChannel.accept();
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                            System.out.println(channel.getRemoteAddress() + "上线了");
                        }
                        if (selectionKey.isReadable()) {
                            SocketChannel channel = (SocketChannel) selectionKey.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1004);
                            channel.read(byteBuffer);
                            String msg = new String(byteBuffer.array());
                            System.out.println("from :" + channel.getRemoteAddress() + "信息：" + msg);
                            sendMsg(msg, channel);
                        }
                        keyIterator.remove();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMsg(String msg, SocketChannel self) {

        try {
            for (SelectionKey key : selector.keys()) {
                Channel channel = key.channel();
                if (channel instanceof SocketChannel && channel != self) {

                    ((SocketChannel) channel).write(ByteBuffer.wrap(msg.getBytes()));

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        server.listen();
    }
}
