package com.mgw.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNio4 {

    public static void main(String[] args) throws Exception{

        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);

        while (true) {

            //buffer.clear();

            int read = inputStreamChannel.read(buffer);

            System.out.println("read : " + read);

            if(-1 == read) {
                break;
            }

            buffer.flip();

            outputStreamChannel.write(buffer);

        }

        inputStreamChannel.close();
        outputStreamChannel.close();


    }
}
