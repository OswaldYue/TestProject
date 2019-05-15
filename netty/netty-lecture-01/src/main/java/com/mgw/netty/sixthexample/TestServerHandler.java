package com.mgw.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfoPerson.MyMessage> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfoPerson.MyMessage msg) throws Exception {

        MyDataInfoPerson.MyMessage.DataType dataType = msg.getDataType();

        if (dataType == MyDataInfoPerson.MyMessage.DataType.PersonType) {

            System.out.println("person ==>");
            System.out.println(msg);

        }else if (dataType == MyDataInfoPerson.MyMessage.DataType.DogType) {

            System.out.println("dog ==>");
            System.out.println(msg);

        }else {

            System.out.println("cat ==>");
            System.out.println(msg);

        }
        

    }

}
