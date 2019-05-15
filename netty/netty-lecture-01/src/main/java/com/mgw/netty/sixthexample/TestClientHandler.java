package com.mgw.netty.sixthexample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.Student msg) throws Exception {


    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        int randomInt = new Random().nextInt(3);

        MyDataInfoPerson.MyMessage message = null;

        System.out.println("数字:"+ randomInt);

        if (0 == randomInt) {

             message = MyDataInfoPerson.MyMessage.newBuilder().
                    setDataType(MyDataInfoPerson.MyMessage.DataType.PersonType).
                    setPerson(MyDataInfoPerson.Person.newBuilder().
                            setName("zs").setAge(20).setAddress("GZ").build()).
                     build();


        }else if (1 == randomInt) {
            message = MyDataInfoPerson.MyMessage.newBuilder().
                    setDataType(MyDataInfoPerson.MyMessage.DataType.DogType).
                    setDog(MyDataInfoPerson.Dog.newBuilder().setName("dog").setAge(2).build()).
                    build();

        }else {
            message = MyDataInfoPerson.MyMessage.newBuilder().
                    setDataType(MyDataInfoPerson.MyMessage.DataType.CatType).
                    setCat(MyDataInfoPerson.Cat.newBuilder().setName("cat").setCity("SZ").build()).
                    build();
        }

        System.out.println(message);


//        MyDataInfo.Student student = MyDataInfo.Student.newBuilder().
//                setName("ls").setAge(30).setAddress("sz").build();
//
//        ctx.channel().writeAndFlush(student);

        ctx.channel().writeAndFlush(message);

    }
}
