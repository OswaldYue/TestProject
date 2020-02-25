package com.mgw.classload.chapter1;

import java.util.Random;

public class ClassActiveUse {

    public static void main(String[] args) throws ClassNotFoundException {

        // 1.new，直接使用
//        new Obj();

        //2.访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
//        System.out.println(I.a);
//        System.out.println(Obj.salary);

        //3.调用静态方法
//        Obj.printSalary();

        //4.反射某个类
//        Class.forName("com.mgw.classload.chapter1.Obj");

        //5.初始化一个子类
        //Obj 被初始化.
        //Child 被初始化.
        //父类先初始化 然后子类再初始化
//        System.out.println(Child.age);
        // 如果子类调用父类的属性，则子类不初始化
//        System.out.println(Child.salary);
        //通过数组引用时,不会初始化
//        Obj[] arrays = new Obj[10];
        //引用常量时不会初始化，因为常量在编译期间会被放进常量池中
//        System.out.println(Obj.XX);
        //但是当常量无法在编译时确认时则会初始化
        System.out.println(Obj.RANDOM);

        //6.启动类，main函数的类 例如:ClassActiveUse此类的main方法


    }

}

class Obj{

    public static long salary = 1000000;

    public static final int XX = 200;

    public static final int RANDOM = new Random().nextInt(100);

    static {
        System.out.println("Obj 被初始化.");
    }

    public static void printSalary() {
        System.out.println("=======Obj的salary="+ salary);
    }
}

class Child extends Obj {

    public static int age = 30;

    static {
        System.out.println("Child 被初始化.");
    }


}

interface I {

    int a = 10;
}
//访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
//1.对某个类的静态变量进行读写 ->初始化class
//2.对接口中静态变量进行读取 ->初始化interface
