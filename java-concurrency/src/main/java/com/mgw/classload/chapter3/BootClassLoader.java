package com.mgw.classload.chapter3;

import java.util.Arrays;

public class BootClassLoader {

    public static void main(String[] args) {

        String propertites = System.getProperty("sun.boot.class.path");
        String[] strings = propertites.split(";");
        /*
        D:\jdk8u144\jre\lib\resources.jar
        D:\jdk8u144\jre\lib\rt.jar
        D:\jdk8u144\jre\lib\sunrsasign.jar
        D:\jdk8u144\jre\lib\jsse.jar
        D:\jdk8u144\jre\lib\jce.jar
        D:\jdk8u144\jre\lib\charsets.jar
        D:\jdk8u144\jre\lib\jfr.jar
        D:\jdk8u144\jre\classes
        * */
        Arrays.asList(strings).forEach(System.out :: println);

    }


}
