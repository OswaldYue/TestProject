package com.poplar.classload;

public class ClassLoadTest22 {

    static {
        System.out.println("ClassLoadTest22 static block invoked");
    }

    //扩展类加载器只加载jar包，需要把class文件打成jar  jar cvf test.jar com/poplar/classload/ClassLoadTest15.class
    //此列子中将扩展类加载的位置改成了当前的classes目录 java -Djava.ext.dirs ./ com.poplar.classload.ClassLoadTest22
    public static void main(String[] args) {
        System.out.println(ClassLoadTest22.class.getClassLoader());
        System.out.println(ClassLoadTest15.class.getClassLoader());
    }
}
