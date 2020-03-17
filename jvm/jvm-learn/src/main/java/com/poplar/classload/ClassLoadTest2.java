package com.poplar.classload;

/**
 * <p>常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化，且不会加载定义常量的类
 * <b>注意:</b>这里指的是将常量存到ClassLoadTest2的常量池中，之后ClassLoadTest2和Student就没有任何关系了。
 * 甚至我们可以将Student的class文件删除</p>
 * <h2>常见的注记符</h2>
 * <p>助记符 ldc：表示将int、float或者String类型的常量值从常量池中推送至栈顶</p>
 * <p>助记符 bipush：表示将单字节（-128-127）的常量值推送到栈顶</p>
 * <p>助记符 sipush：表示将一个短整型值（-32767-32768）推送至栈顶</p>
 * <p>助记符 iconst_1：表示将int型的1推送至栈顶（常用的从-1对应是iconst_m1,0到5对应的是iconst_0-iconst_5）</p>
 * <p>这些助记符都是由底层类支持的，例如iconst_1 对应ICONST类，ldc对应LDC等等</p>
 */
public class ClassLoadTest2 {
    public static void main(String[] args) {
//        System.out.println(Student.str);

        // 对于编译阶段能确定的常量，会被存入调用这个常量的方法所在的类的常量池之中,
        // 因此并不会触发定义常量的类的初始化，且不会加载定义常量的类
//        System.out.println(Student.str1);

        // 使用bipush 可以javap -v ClassLoadTest2.class 查看
//        System.out.println(Student.n);

        // 使用sipush
//        System.out.println(Student.i);

        // 使用iconst_1
//        System.out.println(Student.s);

        // 使用iconst_5
//        System.out.println(Student.f);

        // 使用bipush
//        System.out.println(Student.m);

        // 使用iconst_0
//        System.out.println(Student.g);

        // 使用iconst_m1
        System.out.println(Student.g1);

        // 使用bipush
        System.out.println(Student.g2);


    }
}

class Student {

    static String str = "hello world";
    static final String str1 = "hello world MMM";
    static final short n = 127;
    static final int i = 128;
    static final int s = 1;
    static final int f = 5;
    static final int m = 6;
    static final int g = 0;
    static final int g1 = -1;
    static final int g2 = -2;

    static {
        System.out.println("Student static blocking");
    }
}