package com.mgw.ioc;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class IocTest  {

    public static void test1() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        Car bean = context.getBean(BWM.class);

        System.out.println(bean);


    }

    public static void test2() {

        ClassPathResource resource = new ClassPathResource("applicationcontext.xml");
        XmlBeanFactory factory = new XmlBeanFactory(resource);

        Car bean = factory.getBean(BWM.class);

        System.out.println(bean);


    }

    public static void main(String[] args) {

//        System.out.println("=====================================================");
//        test1();

        System.out.println("=====================================================");
        test2();
    }

}
