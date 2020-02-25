package com.mgw.classload.chapter1;

public class Singleton2 {

    private static Singleton2 instance = new Singleton2();

    public static int x = 0;

    public static int y;



    /*
    * 过程分析:
    * 链接的准备阶段，赋默认值:
    *   instance=null
    *   x=0
    *   y=0
    *
    *
    * 初始化阶段，赋正确的值:
    *   instance=new Singleton()触发构造函数代码,此时x,y都为1
    *   x=0
    *   y没有主动赋值，按原值
    *
    *   所以结果是x=0,y=1
    *   最后x只是又被赋值为0罢了
    *
    * */


    private Singleton2() {
        x++;
        y++;
    }

    public static Singleton2 getInstance() {

        return instance;
    }

    public static void main(String[] args) {
//        Singleton singleton = getInstance();
        System.out.println(Singleton2.x); //0
        System.out.println(Singleton2.y); //1
    }

}
