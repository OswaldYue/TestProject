package com.poplar.bytecode.proxy;

public class RealSubject implements Subject {
    @Override
    public void request(String name) {
        System.out.println("real subject invoke");
        System.out.println("name is : " + name);
    }
}
