package com.mgw.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * 1.netty用来做http服务器
 *
 * */
public class TestServer {

    public static void main(String[] args) throws Exception{

        // 定义两个事件循环组 其实就是线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).
                    childHandler(new TestServerInitializer());

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
