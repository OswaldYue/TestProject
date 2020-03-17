package com.poplar.classload;

public class ClassLoadTest18_1 {

    public static void main(String[] args) throws Exception {

        ClassLoadTest16 loader1 = new ClassLoadTest16("loader1");
        loader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> loadClass = loader1.loadClass("com.poplar.classload.ClassLoadTest15");
        System.out.println("class : " + loadClass);
        System.out.println("class loader:" + loadClass.getClassLoader());
    }
}
