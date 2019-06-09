package com.mgw.netty.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class NioServer {

    private static Map<String,SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            try {
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                selectionKeys.forEach(selectionKey -> {

                    final SocketChannel client;

                    try {

                        if (selectionKey.isAcceptable()) {
                            ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                            client = server.accept();

                            client.configureBlocking(false);
                            client.register(selector,SelectionKey.OP_READ);

                            String key = "[" + UUID.randomUUID().toString() + "]";

                            clientMap.put(key,client);

                        }else if (selectionKey.isReadable()) {


                        }

                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                });



            }catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

}
