package com.mgw.netty.nio;

import java.nio.ByteBuffer;

/**
 * JAVA NIO的只读buffer
 *
 * */
public class TestNio7 {

    public static void main(String[] args) throws Exception{

        ByteBuffer byteBuffer = ByteBuffer.allocate(10);

        for (int i = 0 ; i < byteBuffer.capacity() ; i++) {

            byteBuffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();


    }
}
