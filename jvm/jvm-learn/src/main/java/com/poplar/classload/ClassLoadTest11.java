package com.poplar.classload;

/**
 * Created By poplar on 2019/11/7
 */
public class ClassLoadTest11 {


    public static void main(String[] args) {
        System.out.println(Child3.a);
        System.out.println("---------");
        Child3.doSomething();

//        输出:
//        Parent3 static block
//        2
//        ---------
//        do something
    }
}

class Parent3 {
    static int a = 2;

    static {
        System.out.println("Parent3 static block");
    }

    static void doSomething() {
        System.out.println("do something");
    }
}

class Child3 extends Parent3 {
    static int b = 3;

    static {
        System.out.println("Child3 static block");
    }
}