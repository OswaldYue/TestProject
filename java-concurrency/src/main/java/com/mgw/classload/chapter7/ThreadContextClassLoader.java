package com.mgw.classload.chapter7;

import com.mgw.classload.chapter4.MyClassLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ThreadContextClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        final ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        // sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(contextClassLoader);

        Thread.currentThread().setContextClassLoader(new MyClassLoader());

        // com.mgw.classload.chapter4.MyClassLoader@74a14482
        System.out.println(Thread.currentThread().getContextClassLoader());

        // Thread.currentThread().getContextClassLoader() 其实就是jdk开了一个后门，允许你去破坏双亲委托机制
        Class.forName("com.mysql.jdbc.Driver");
        final Connection connection = DriverManager.getConnection("");
    }

}
