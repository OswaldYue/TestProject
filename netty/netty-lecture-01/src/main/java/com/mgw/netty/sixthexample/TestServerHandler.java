package com.mgw.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfoPerson.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfoPerson.MyMessage msg) throws Exception {

        System.out.println(msg);
    }

}
