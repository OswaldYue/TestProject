package com.mgw.classload.chapter1;

public class Singleton {



    public static int x = 0;

    public static int y;

    private static Singleton instance = new Singleton();

    /*
    * 过程分析:
    * 链接的准备阶段，赋默认值:
    *   x=0
    *   y=0
    *   instance=null
    *
    * 初始化阶段，赋正确的值:
    *   x=0
    *   y没有主动赋值，按默认值
    *   instance=new Singleton()触发构造函数代码
    * */


    private Singleton() {
        x++;
        y++;
    }

    public static Singleton getInstance() {

        return instance;
    }

    public static void main(String[] args) {
//        Singleton singleton = getInstance();
        System.out.println(Singleton.x); //1
        System.out.println(Singleton.y); //1
    }

}
