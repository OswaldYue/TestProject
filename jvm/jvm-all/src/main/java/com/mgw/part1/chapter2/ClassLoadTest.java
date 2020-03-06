package com.mgw.part1.chapter2;

public class ClassLoadTest {

    public static void main(String[] args) {

        // 获取系统类加载器
        final ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);

        // 获取扩展类加载器
        final ClassLoader extClassLoader = systemClassLoader.getParent();
        // sun.misc.Launcher$ExtClassLoader@4554617c
        System.out.println(extClassLoader);

        // 试图获取bootstarpClassLoad
        final ClassLoader classLoader = extClassLoader.getParent();
        // null 无法获取到
        System.out.println(classLoader);

        // 对于当前自定义类来说
        final ClassLoader loader = ClassLoadTest.class.getClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2 获取到的是系统类加载器 这个系统类加载器是单例的
        System.out.println(loader);


        final ClassLoader stringClassLoader = String.class.getClassLoader();
        // String 是由bootstarpClassLoad加载的 ---> java的核心类库就是使用此加载器加载的
        System.out.println(stringClassLoader);



    }

}
