package com.mgw.netty.nio;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNio3 {

    public static void main(String[] args) throws Exception{

        FileOutputStream fileOutputStream = new FileOutputStream("TestNio3.txt");

        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        byte[] message = "2eqe weqwe awda".getBytes();
        for (byte b : message) {
            byteBuffer.put(b);
        }

        byteBuffer.flip();

        channel.write(byteBuffer);

        fileOutputStream.close();



    }

}
