package com.mgw.classload.chapter5;

import com.mgw.classload.chapter4.MyClassLoader;

public class DecryptClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {

        // 先将class源文件进行加密处理
        EncryptUtils.doEncrypt("E:\\code\\AppData\\classloader1\\com\\mgw\\classload\\chapter4\\MyObject.class",
                "E:\\code\\AppData\\classloader3\\com\\mgw\\classload\\chapter4\\MyObject.class");

        //Exception in thread "main" java.lang.ClassFormatError:  Incompatible magic value 889275713 in class file com/mgw/classload/chapter4/MyObject
//        final MyClassLoader myClassLoader = new MyClassLoader();
        // 必须先要进行解密才可
        final DecryptClassLoader myClassLoader = new DecryptClassLoader();
        myClassLoader.setDir("E:\\code\\AppData\\classloader3");
        final Class<?> aClass = myClassLoader.loadClass("com.mgw.classload.chapter4.MyObject");

        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
    }
}
