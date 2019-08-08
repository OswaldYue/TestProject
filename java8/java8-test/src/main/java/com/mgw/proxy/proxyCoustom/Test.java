package com.mgw.proxy.proxyCoustom;

import com.mgw.proxy.proxyCoustom.dao.UserDao;
import com.mgw.proxy.proxyCoustom.dao.UserDaoImpl;
import com.mgw.proxy.proxyCoustom.utils.JDKInvocationHandlerImpl;
import com.mgw.proxy.proxyCoustom.utils.ProxyInvocationHandlerImpl;
import com.mgw.proxy.proxyCoustom.utils.ProxyUtil1;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Proxy;

public class Test {

    public static void test1() {

        UserDao userDao = new UserDaoImpl();

        try {
            long begin1 = System.currentTimeMillis();
            UserDao proxy = (UserDao) ProxyUtil1.newProxyInstance(UserDao.class,new ProxyInvocationHandlerImpl(userDao));
            proxy.query();
            long end1 = System.currentTimeMillis();

            System.out.println("test time1 = " + (begin1 - end1));

//            System.out.println("-------------------------分割线-----------------------");
//            String s = proxy.proxy();
//            System.out.println(s);
//            System.out.println(proxy);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long begin2 = System.currentTimeMillis();
        UserDao jdkProxy = (UserDao)Proxy.newProxyInstance(Test.class.getClassLoader(),new Class[]{UserDao.class},new JDKInvocationHandlerImpl(userDao));
        jdkProxy.query();
        long end2 = System.currentTimeMillis();
        System.out.println("test time2 = " + (begin2 - end2));
    }

    public static void test2() {


        Class[] interfaces = {UserDao.class};
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy10", interfaces);

        File file = new File("D:\\04.test-code\\java8\\java8-test\\build\\classes\\java\\main\\com\\mgw\\proxy\\$Proxy10.class");

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

        System.out.println("-----------------------------------------------");
//        test1();
        test2();
    }
}
