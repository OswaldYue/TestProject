package com.mgw.netty.nio;

import java.nio.ByteBuffer;

public class TestNio5 {

    public static void main(String[] args) throws Exception{

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        byteBuffer.putInt(15);
        byteBuffer.putLong(50000000L);
        byteBuffer.putDouble(12.1212123);
        byteBuffer.putChar('嗨');
        byteBuffer.putShort((short)2);
        byteBuffer.putChar('仨');

        byteBuffer.flip();

        //怎么放置类型的  就要按什么样的类型去取
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getChar());


    }
}
