package com.mgw.classload.chapter4;

/**
 * 1.类加载器的委托机制是优先交给父加载器先去尝试加载
 * 2.父加载器和子加载器其实是一种包装关系，或者是包含关系
 * */
public class MyClassLoaderTest3 {

    public static void main(String[] args) throws ClassNotFoundException {

        MyClassLoader myClassLoader1 = new MyClassLoader("MyClassLoader1");
        MyClassLoader myClassLoader2 = new MyClassLoader("MyClassLoader2");

        myClassLoader2.setDir("E:\\code\\AppData\\classloader1");

        // com.mgw.classload.chapter4.MyClassLoader@74a14482
        // com.mgw.classload.chapter4.MyClassLoader@1540e19d
        System.out.println(myClassLoader1);
        System.out.println(myClassLoader2);

        final Class<?> aClass = myClassLoader1.loadClass("com.mgw.classload.chapter4.MyObject");
        final Class<?> bClass = myClassLoader2.loadClass("com.mgw.classload.chapter4.MyObject");

        // 2133927002
        // 1836019240
        // 此时因为不是同一个加载器,所以加载出来的类就不同
        System.out.println(aClass.hashCode());
        System.out.println(bClass.hashCode());


    }

}
