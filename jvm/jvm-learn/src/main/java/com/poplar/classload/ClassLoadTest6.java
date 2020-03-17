package com.poplar.classload;

/**
 * Created By poplar on 2019/11/7
 * 准备和初始化阶段静态变量赋值问题
 */
public class ClassLoadTest6 {

    public static void main(String[] args) {
        System.out.println(Singleton.getInstance());
        System.out.println(Singleton.a);//默认值 0 -> 初始化 1 -> 构造函数 2
        System.out.println(Singleton.b);//默认值0 -> 构造函数 1 -> 初始化 0
        System.out.println(ClassLoadTest6.class.getClassLoader());
    }
}

class Singleton {

    public static int a = 1;

    private static Singleton instance = new Singleton();

    private Singleton() {
        a++;
        b++;
        System.out.println(a);//2
        System.out.println(b);//1
    }

    public static int b = 0;

    public static Singleton getInstance() {
        return instance;
    }
}