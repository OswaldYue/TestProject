package com.mgw.netty.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

public class TestNio1 {

    public static void main(String[] args) {

        IntBuffer intBuffer = IntBuffer.allocate(10);
        System.out.println("init capacity : " + intBuffer.limit());
        for (int i = 0 ; i < 5 ; i++) {

            int anInt = new SecureRandom().nextInt(20);

            intBuffer.put(anInt);
        }

        System.out.println("before flip limit : " + intBuffer.limit());

        intBuffer.flip();

        System.out.println("after flip limit : " + intBuffer.limit());

        while (intBuffer.hasRemaining()) {

            System.out.println("postion : " + intBuffer.position());
            System.out.println("limit : " + intBuffer.limit());
            System.out.println("capacity : " + intBuffer.capacity());

            System.out.println(intBuffer.get());
        }
    }
}
