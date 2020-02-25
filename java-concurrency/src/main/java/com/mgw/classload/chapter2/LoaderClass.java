package com.mgw.classload.chapter2;

public class LoaderClass {

    public static void main(String[] args) {

        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        MyObject myObject3 = new MyObject();
        MyObject myObject4 = new MyObject();

        System.out.println(myObject1.getClass() == myObject2.getClass());
        System.out.println(myObject1.getClass() == myObject3.getClass());
        System.out.println(myObject1.getClass() == myObject4.getClass());

        Class<MyObject> myObjectClass = MyObject.class;
        System.out.println(myObject1.getClass() == myObjectClass);// true

        System.out.println(MyObject.x);
        System.out.println(Sub.x);

    }

}


class MyObject {

    public static int x = 10;

    static {
        System.out.println(x);
        x= 10+1;

        y = 200;

        //静态语句块中只能访问到定义在静态语句块之前的变量，定义在他之后的变量，只能赋值，不能访问
        //Error:(36, 28) java: 非法前向引用
//        System.out.println(y);
    }
    private static int y = 20;

}

class Parent {

    static {
        System.out.println("Parent类");
    }
}

class Sub extends Parent{

    public static int x = 100;

    static {
        System.out.println("Sub子类");
    }


}

class Test1{

    // 没有静态代码块也会将静态变量放进静态代码块中进行初始化
    public static int y = 100;
}

class Test2{

    //在<clinit>方法(类构造器(静态代码块))中进行初始化
    public static int y = 100;

    // 在<init>构造函数(对象构造器)中进行初始化
    private int x = 0;

    public Test2() {
        x = 5;
    }
}