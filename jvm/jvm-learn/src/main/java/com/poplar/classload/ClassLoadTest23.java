package com.poplar.classload;

import sun.misc.Launcher;

/**
 *
 * 在运行期，一个Java类是由该类的完全限定名（binary name）和用于加载该类的定义类加载器所共同决定的。
 * 如果同样名字（完全相同限定名）是由两个不同的加载器所加载，那么这些类就是不同的，即便.class文件字节码相同，并且从相同的位置加载亦如此。
 * 在oracle的hotspot，系统属性sun.boot.class.path如果修改错了，则运行会出错：
 * Error occurred during initialization of VM
 * java/lang/NoClassDeFoundError: java/lang/Object
 */
/**
 * 类加载器本身也是类加载器，类加载器又是谁加载的呢？？（先有鸡还是现有蛋）
 * 类加载器是由启动类加载器去加载的，启动类加载器是C++写的，内嵌在JVM中
 *
 * 内嵌于JVM中的启动类加载器会加载java.lang.ClassLoader以及其他的Java平台类。
 * 当JVM启动时，一块特殊的机器码会运行，它会加载扩展类加载器以及系统类加载器，这块特殊的机器码叫做启动类加载器
 *
 * 启动类加载器并不是java类，其他的加载器都是java类
 * 启动类加载器是特定于平台的机器指令，它负责开启整个加载过程
 *
 * 所有类加载器(除了启动类加载器)都被实现为java类，不过，总归要有一个组件来加载第一个java类加载器，从而让整个加载过程能够顺利
 * 进行下去，加载第一个纯java类加载器就是启动类加载器的职责
 *
 * 启动类加载器还会负责加载JRE正常运行所需要的基本组件，这包括java.util与java.lang包中的类等等
 * */
public class ClassLoadTest23 {
    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));//根加载器路径
        System.out.println(System.getProperty("java.ext.dirs"));//扩展类加载器路径
        System.out.println(System.getProperty("java.class.path"));//应用类加载器路径

        // null
        System.out.println(ClassLoader.class.getClassLoader());
        //此处由于系统和扩展类加载器都是Launcher其内部静态类，但又都是非public的，
        // 所以不能直接获取他们的类加载器，方法就是通过获取他们的外部类加载器是谁？从而确当他们的类加载器。
        // null
        System.out.println(Launcher.class.getClassLoader());
        // sun.misc.Launcher@6d6f6e28
        System.out.println(Launcher.getLauncher());

        // {@java.lang.ClassLoader.getSystemClassLoader()} 这个方法注释有说明
        //java.system.class.loader这个系统属性可以修改系统类加载器，让其变成我们自定义的classloader，
        // 例如：java -Djava.system.class.loader=com.poplar.classload.ClassLoadTest16 com.poplar.classload.ClassLoadTest23
        //默认情况下，java.system.class.loader这个属性没有被定义，如果没有指定则默认为AppClassLoader
        //就算是指定了java.system.class.loader属性，改变了系统加载器为我们自定义的类加载器，但是其父类加载器也会被jvm指定为AppClassLoader，
        // 且由默认的类加载器加载我们的自定义类加载器，实验表明也是AppClassLoader
        //
        // nulll
        System.out.println(System.getProperty("java.system.class.loader"));

        // 以下代码的执行在控制台 java -Djava.system.class.loader=com.poplar.classload.ClassLoadTest16 com.poplar.classload.ClassLoadTest23
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoadTest23.class.getClassLoader());
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(ClassLoadTest16.class.getClassLoader());
        // com.poplar.classload.ClassLoadTest16@65aac53b
        System.out.println(ClassLoader.getSystemClassLoader());
    }
}
