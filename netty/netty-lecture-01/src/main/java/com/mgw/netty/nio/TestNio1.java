package com.mgw.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class TestNio1 {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);

        for (int i = 0 ; i < intBuffer.capacity() ; i++) {

            int anInt = new SecureRandom().nextInt(20);


            intBuffer.put(anInt);
        }

        intBuffer.flip();

        while (intBuffer.hasRemaining()) {

            System.out.println(intBuffer.get());
        }
    }
}
