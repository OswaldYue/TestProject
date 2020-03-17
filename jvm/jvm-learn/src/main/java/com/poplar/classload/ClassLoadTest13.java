package com.poplar.classload;

public class ClassLoadTest13 {

    public static void main(String[] args) {

     /*
     * Returns the system class loader for delegation.  This is the default
     * delegation parent for new <tt>ClassLoader</tt> instances, and is
     * typically the class loader used to start the application
     * 这个方法很重要，直接看其doc文档即可
      */
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);

        //sun.misc.Launcher$ExtClassLoader@6d6f6e28
        //null
        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}
