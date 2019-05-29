package com.mgw.netty.nio;

import java.nio.ByteBuffer;

/**
 * JAVA NIO的分片buffer
 *
 * */
public class TestNio6 {

    public static void main(String[] args) throws Exception{


        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for (int i = 0; i < byteBuffer.capacity() ; i++) {

            byteBuffer.put((byte)i);

        }

        byteBuffer.position(2);
        byteBuffer.limit(6);

        //实际公用原始的buffer
        ByteBuffer newBuffer = byteBuffer.slice();

        for (int i = 0; i < newBuffer.capacity() ;i++) {

            byte b = newBuffer.get(i);
            b *= 2;
            newBuffer.put(i,b);

        }
        byteBuffer.position(0);
        byteBuffer.limit(byteBuffer.capacity());

        while (byteBuffer.hasRemaining()) {

            System.out.println(byteBuffer.get());
        }


    }
}
