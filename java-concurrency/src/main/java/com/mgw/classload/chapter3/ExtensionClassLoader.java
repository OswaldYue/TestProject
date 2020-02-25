package com.mgw.classload.chapter3;

import java.util.Arrays;

public class ExtensionClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {

        final String propertites = System.getProperty("java.ext.dirs");
        final String[] strings = propertites.split(";");

        /*
        D:\jdk8u144\jre\lib\ext
        C:\WINDOWS\Sun\Java\lib\ext
        * */
        Arrays.asList(strings).forEach(System.out::println);

        Class<?> aClass = Class.forName("com.mgw.classload.chapter3.SimpleObject");
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(aClass.getClassLoader());
        //sun.misc.Launcher$ExtClassLoader@41629346
        System.out.println(aClass.getClassLoader().getParent());

        Class<?> clazz = Class.forName("java.lang.String");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());

    }
}
