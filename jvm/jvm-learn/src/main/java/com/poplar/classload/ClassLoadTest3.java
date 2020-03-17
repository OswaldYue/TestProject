package com.poplar.classload;

import java.util.UUID;

/**
 * Created By poplar on 2019/11/6
 * <p>
 * 当一个常量的值并非编译期间可以确定的，那么其值就不会放到调用类的常量池中
 * 这时在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
 * </p>
 */
public class ClassLoadTest3 {

    public static void main(String[] args) {
        //Student2 static block
        //70e5e2ff-7637-4edb-bf6c-b72da212e801
        System.out.println(Student2.str);
    }
}

class Student2 {
    static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("Student2 static block");
    }
}
