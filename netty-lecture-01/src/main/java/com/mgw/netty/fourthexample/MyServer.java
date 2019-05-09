package com.mgw.netty.fourthexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 案例:心跳检测
 *
 * */
public class MyServer {

    public static void main(String[] args) throws Exception{


        //初始化两个线程池
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    handler(new LoggingHandler(LogLevel.INFO)).
                    childHandler(new MyServerInitializer());

            ChannelFuture sync = serverBootstrap.bind(8899).sync();


            sync.channel().closeFuture().sync();
        }catch (Exception e) {

            e.printStackTrace();

        }finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }

    }

}
