package com.mgw.two.chapter7;

import java.util.stream.IntStream;

public class ImmutableClient {

    public static void main(String[] args) {

        //共享数据
        Person person = new Person("MMM", "XiAn");
        IntStream.range(0,5).forEach(i -> {

            new UsePersonThread(person).start();
        });


    }
}
