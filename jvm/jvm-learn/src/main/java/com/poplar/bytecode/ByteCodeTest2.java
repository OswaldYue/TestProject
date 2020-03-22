package com.poplar.bytecode;

/**
 *
 * 从字节码分析得出的结论：
 * 1.成员变量的初始化是在构造方法中完成的，有多少个构造方法，初始化指令就会调用几次,，但是赋默认空值不是在构造方法中进行的
 * 成员变量的赋值过程如下:
 *  默认空值，构造方法中初始化值，最后再执行构造方法中代码
 * 2.当有构造代码块时，构造代码块的执行同样在构造方法中，构造代码与成员变量初始化的顺序是按照代码的书写顺序，但两者都要先于构造方法，
 * 成员变量的赋值过程如下：
 *  默认空值，构造方法中先执行初始化还是先执行构造代码块的内容，看代码的写作顺序，最后执行构造方法
 * 3.静态成员变量同样是在clinit方法完成的，不管有多少个静态变量都是在该方法完成初始化
 */
public class ByteCodeTest2 {

    String str = "Welcome";

    private int x = 5;

    public static Integer in = 10;

    {
        y = 1;
        System.out.println("MM");
    }

    public ByteCodeTest2(String str) {
        this.str = str;
    }

    public ByteCodeTest2(String str, int x) {
        this.x = x;
        this.str = str;
        this.y = 1000;
        System.out.println(this.z);
        // 以此构造方法为例，说明代码执行顺序：
        /**
         * str初始化为"Welcome"
         * x初始化为5
         * y赋值为1
         * 打印"MM"
         * 打印"HH"
         * y赋值为10
         * y初始化为100
         * 执行构造方法
         * this.x = x
         * this.str = str
         * y赋值为1000
         * 打印z为0
         *
         * */
    }

    public ByteCodeTest2() {

    }

    {
        System.out.println("HH");
        y = 10;
    }

    private int y = 100;

    private int z ;

    public static void main(String[] args) {
        ByteCodeTest2 byteCodeTest2 = new ByteCodeTest2();
        byteCodeTest2.setX(8);
        in = 20;
    }

    private synchronized void setX(int x) {
        this.x = x;
    }

    public void test(String str) {
        synchronized (this) {//给当前对象上锁
            System.out.println("Hello World");
        }
    }

    //给类字节码码上锁
    public static synchronized void test() {
    }

    static {
        System.out.println();
    }
}
