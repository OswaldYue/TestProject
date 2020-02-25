package com.mgw.classload.chapter6;

public class NameSpace {

    public static void main(String[] args) throws ClassNotFoundException {

        final ClassLoader classLoader = NameSpace.class.getClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);
        final Class<?> aClass = classLoader.loadClass("java.lang.String");
        final Class<?> bClass = classLoader.loadClass("java.lang.String");

        // 都是由根加载器加载的，当发现已经被加载到了堆上了，就直接去拿已经存在的
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());




    }
}
