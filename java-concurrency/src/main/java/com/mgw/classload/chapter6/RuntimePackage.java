package com.mgw.classload.chapter6;

public class RuntimePackage {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        final SimpleClassLoader simpleClassLoader = new SimpleClassLoader();
        final Class<?> aClass = simpleClassLoader.loadClass("com.mgw.classload.chapter6.SimpleObject");

//        System.out.println(aClass);
//        System.out.println(aClass.getClassLoader());

        // Exception in thread "main" java.lang.ClassCastException: com.mgw.classload.chapter6.SimpleObject cannot be cast to com.mgw.classload.chapter6.SimpleObject
        // 出现这个错误就是因为运行时包造成的
        SimpleObject o = (SimpleObject)aClass.newInstance();

    }
}
