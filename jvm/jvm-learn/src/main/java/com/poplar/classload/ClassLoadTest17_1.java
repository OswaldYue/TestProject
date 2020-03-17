package com.poplar.classload;


public class ClassLoadTest17_1 {
    public static void main(String[] args) throws Exception {
        ClassLoadTest16 loader1 = new ClassLoadTest16("loader1");
        loader1.setPath("E:\\code\\AppData\\classloader4\\");

        Class<?> clazz = loader1.loadClass("com.poplar.classload.Simple");
        System.out.println("class : " + clazz.hashCode());
        //如果注释掉该行，就并不会实例化Simple对象，即Sample构造方法不会被执行，
        // 因此不会实例化MyCat对象，但是会加载Simple类不加载MyCat类
        Object instance = clazz.newInstance();//实列化Simple和MyCat
    }
}
