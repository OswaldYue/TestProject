package com.mgw.classload.chapter6;

public class SimpleClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {

        final SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        final Class<?> aClass = simpleClassLoader.loadClass("com.mgw.classload.chapter6.SimpleObject");

        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());

        final Class<?> aClass1 = simpleClassLoader.loadClass("java.lang.String");
        System.out.println(aClass1);
        System.out.println(aClass1.getClassLoader());
    }
}
