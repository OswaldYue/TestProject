package com.mgw.netty.secondexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 2.netty用来开发socket程序
 * */
public class MyServer {

    public static void main(String[] args) throws Exception{

        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boosGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new MyServerInitalizer());

            ChannelFuture channelFuture = bootstrap.bind(8899).sync();

            channelFuture.channel().closeFuture().sync();


        }finally {

            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }




    }


}
