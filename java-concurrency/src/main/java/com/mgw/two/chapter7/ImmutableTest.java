package com.mgw.two.chapter7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableTest {

    private final int age;

    private final String name;

    private final List<String> list;


    public ImmutableTest(int age, String name) {
        this.age = age;
        this.name = name;
        this.list = new ArrayList<>();
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    public static void main(String[] args) {

        String a = "Hello";
        String b = a.replace("l", "K");

        System.out.println(a.getClass() + "   " + a.hashCode());
        System.out.println(b.getClass() + "   " + b.hashCode());

    }
}
