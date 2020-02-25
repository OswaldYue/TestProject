package com.mgw.classload.chapter4;

/**
 * 1.类加载器的委托机制是优先交给父加载器先去尝试加载
 * 2.父加载器和子加载器其实是一种包装关系，或者是包含关系
 * */
public class MyClassLoaderTest2 {

    public static void main(String[] args) throws ClassNotFoundException {

        MyClassLoader myClassLoader1 = new MyClassLoader("MyClassLoader1");
        // 以myClassLoader1作为父加载器
        MyClassLoader myClassLoader2 = new MyClassLoader("MyClassLoader2",myClassLoader1);

        // 实际上myClassLoader2加载的目录下没有MyObject类 但是依然可以加载 因为父加载器myClassLoader1可以加载
        myClassLoader2.setDir("E:\\code\\AppData\\classloader2");

        final Class<?> aClass = myClassLoader2.loadClass("com.mgw.classload.chapter4.MyObject");

        System.out.println(aClass);
        // com.mgw.classload.chapter4.MyClassLoader@677327b6
        System.out.println(aClass.getClassLoader());
        // MyClassLoader1
        System.out.println(((MyClassLoader)aClass.getClassLoader()).getClassLoaderName());

    }

}
