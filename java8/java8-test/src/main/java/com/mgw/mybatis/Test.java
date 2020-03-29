package com.mgw.mybatis;

public class Test {

    public static void main(String[] args) {

        B b = new B();

        b.c();
    }
}


class A{

    public void a() {
        System.out.println("A a()...");
    }

    public void c() {
        System.out.println("A c()...");
        a();
    }

}

class B extends A{

    @Override
    public void a() {
        System.out.println("B a()...");
    }
}