package com.mgw.part1.chapter2;

import sun.misc.Launcher;

import java.net.URL;
import java.security.Provider;

public class ClassLoadTest1 {

    public static void main(String[] args) {
        System.out.println("***************启动类加载器***************");
        // 获取BootstrapClassLoad能够加载的class文件的路径
        final URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        /*
        file:/D:/jdk8u144/jre/lib/resources.jar
        file:/D:/jdk8u144/jre/lib/rt.jar
        file:/D:/jdk8u144/jre/lib/sunrsasign.jar
        file:/D:/jdk8u144/jre/lib/jsse.jar
        file:/D:/jdk8u144/jre/lib/jce.jar
        file:/D:/jdk8u144/jre/lib/charsets.jar
        file:/D:/jdk8u144/jre/lib/jfr.jar
        file:/D:/jdk8u144/jre/classes
        */
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        // 从上面路径中随意获取一个类，查看其类加载器: 是BootstrapClassLoad
        final ClassLoader classLoader = Provider.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println("***************扩展类加载器***************");
        // 获取ExtClassLoad能够加载的class文件的路径
        final String extDirs = System.getProperty("java.ext.dirs");
        /*
        D:\jdk8u144\jre\lib\ext
        C:\WINDOWS\Sun\Java\lib\ext
        */
        for (String dir : extDirs.split(";")) {
            System.out.println(dir);
        }

        System.out.println("***************系统类加载器***************");
        final String appDirs = System.getProperty("java.class.path");
        /*
        D:\jdk8u144\jre\lib\charsets.jar
        D:\jdk8u144\jre\lib\deploy.jar
        D:\jdk8u144\jre\lib\ext\access-bridge-64.jar
        D:\jdk8u144\jre\lib\ext\cldrdata.jar
        D:\jdk8u144\jre\lib\ext\dnsns.jar
        D:\jdk8u144\jre\lib\ext\jaccess.jar
        D:\jdk8u144\jre\lib\ext\jfxrt.jar
        D:\jdk8u144\jre\lib\ext\localedata.jar
        D:\jdk8u144\jre\lib\ext\nashorn.jar
        D:\jdk8u144\jre\lib\ext\sunec.jar
        D:\jdk8u144\jre\lib\ext\sunjce_provider.jar
        D:\jdk8u144\jre\lib\ext\sunmscapi.jar
        D:\jdk8u144\jre\lib\ext\sunpkcs11.jar
        D:\jdk8u144\jre\lib\ext\zipfs.jar
        D:\jdk8u144\jre\lib\javaws.jar
        D:\jdk8u144\jre\lib\jce.jar
        D:\jdk8u144\jre\lib\jfr.jar
        D:\jdk8u144\jre\lib\jfxswt.jar
        D:\jdk8u144\jre\lib\jsse.jar
        D:\jdk8u144\jre\lib\management-agent.jar
        D:\jdk8u144\jre\lib\plugin.jar
        D:\jdk8u144\jre\lib\resources.jar
        D:\jdk8u144\jre\lib\rt.jar
        E:\code\githug-code\TestProject\jvm\jvm-all\target\classes
        D:\IntelliJ IDEA 2019.1.3\lib\idea_rt.jar
        */
        for (String dir : appDirs.split(";")) {
            System.out.println(dir);
        }
    }
}
