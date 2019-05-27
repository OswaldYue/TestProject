package com.mgw.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNio2 {

    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("TestNio2.txt");

        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        channel.read(byteBuffer);



        byteBuffer.flip();

        while (byteBuffer.hasRemaining()) {

            byte b = byteBuffer.get();
            System.out.println((char)b);
        }

        fileInputStream.close();
    }

}
