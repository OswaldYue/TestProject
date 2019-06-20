package com.mgw;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class MethodReferenceDemo {

    public static void main(String[] args) {


        List<String> list = Arrays.asList("a", "b", "c", "d");

        list.forEach(System.out::println);

        list.forEach(item -> System.out.println(item));

        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }

}
