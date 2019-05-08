package com.mgw.netty.thirdexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * 案例:使用netty作为通讯工具的聊天程序
 *
 * */
public class MyChatServer {

    public static void main(String[] args) throws Exception{

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new MyChatServerInitializer());

            ChannelFuture channelFuture = bootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();





        }finally {

            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }

    }
}
