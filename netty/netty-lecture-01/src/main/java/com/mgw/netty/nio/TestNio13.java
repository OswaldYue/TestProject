package com.mgw.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class TestNio13 {


    public static void main(String[] args) throws  Exception{


        int[] posts = new int[5];

        posts[0] = 5000;
        posts[1] = 5001;
        posts[2] = 5002;
        posts[3] = 5003;
        posts[4] = 5004;

        Selector selector = Selector.open();

//        System.out.println(SelectorProvider.provider().getClass());
//        System.out.println(sun.nio.ch.DefaultSelectorProvider.create().getClass());

        for (int i = 0; i < posts.length; i++) {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);

            ServerSocket socket = serverSocketChannel.socket();
            socket.bind(new InetSocketAddress(posts[i]));

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("监听端口: " + posts[i]);
        }

        while (true) {

            int numbers = selector.select();
            System.out.println("numbers: " + numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectionKeys: " + selectionKeys);

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel socketChannel = channel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);
                    //移除已经监听到的事件，如果不移除，那么就会继续监听这个事件
                    iterator.remove();

                    System.out.println("获取客户端链接: " + socketChannel);
                }else if (selectionKey.isReadable()) {

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    int byteRead = 0;

                    while (true) {

                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if (read <= 0) {

                            break;
                        }

                        byteBuffer.flip();

                        socketChannel.write(byteBuffer);
                        byteRead += read;

                    }

                    System.out.println("读取 :" + byteRead + ", 来自于: " + socketChannel);

                    iterator.remove();
                }


            }

        }


    }

}
