package com.mgw.netty.thirdexample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MyChatClient {

    public static void main(String[] args) throws Exception{

        NioEventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {

            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).
                    handler(new MyChatClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8899).sync().channel();

            //不断的读取控制台的输入内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            for (;;) {
                channel.writeAndFlush(reader.readLine() + "\r\n");
            }


        }finally {

            eventLoopGroup.shutdownGracefully();
        }

    }
}
