package com.mgw.proxy.JDKdynamicproxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

public class Test {

    public static void test1() {

        Class[] interfaces = {Person.class};
        byte[] bytes = ProxyGenerator.generateProxyClass("PersonProxy", interfaces);

        File file = new File("D:\\04.test-code\\java8\\java8-test\\build\\classes\\java\\main\\com\\mgw\\proxy\\JDKdynamicproxy\\PersonProxy.class");

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        System.out.println("-----------------------------------------------------");
        test1();

    }
}
