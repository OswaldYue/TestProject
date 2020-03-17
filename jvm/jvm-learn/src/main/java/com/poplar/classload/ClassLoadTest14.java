package com.poplar.classload;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class ClassLoadTest14 {

    public static void main(String[] args) throws Exception {

        /*
        Returns the context ClassLoader for this Thread. The context
        ClassLoader is provided by the creator of the thread for use
        */
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();

        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(contextClassLoader);

        String resourceName = "com/poplar/classload/ClassLoadTest13.class";

        /*
        Finds the resource with the given name.  A resource is some data
        (images, audio, text, etc) that can be accessed by class code in a way
        that is independent of the location of the code
        */
        Enumeration<URL> resources = contextClassLoader.getResources(resourceName);

//        file:/E:/code/githug-code/TestProject/jvm/jvm-learn/out/production/classes/com/poplar/classload/ClassLoadTest13.class
        while (resources.hasMoreElements()) {
            System.out.println(resources.nextElement());
        }

        System.out.println("--------------");

        Class<String> stringClass = String.class;
        //null 使用的是boot加载器
        System.out.println(stringClass.getClassLoader());
    }
}
