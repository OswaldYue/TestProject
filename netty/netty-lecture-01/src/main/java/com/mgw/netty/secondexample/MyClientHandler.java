package com.mgw.netty.secondexample;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.time.LocalTime;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {


        Thread.sleep(2000);

        System.out.println(ctx.channel().remoteAddress());
        System.out.println("client output:"+msg);

        ctx.writeAndFlush("from client:"+ LocalTime.now());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     *  当channel活跃时，回调这个事件
     * */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.channel().writeAndFlush("来自客户端的问候!");

    }
}