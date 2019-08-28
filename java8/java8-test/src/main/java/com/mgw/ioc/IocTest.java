package com.mgw.ioc;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

//        Object bwm = factory.getBean("BWM");
        Car bean = factory.getBean(BWM.class);

        System.out.println(bean);


    }

    public static void test3() {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(IocConfig.class);
        context.start();

        BYD byd = context.getBean(BYD.class);

        System.out.println(byd);

    }

    public static void test4() {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(IocConfig.class);
        CarService carService = context.getBean(CarService.class);
        carService.getList();

    }

    public static void main(String[] args) {

//        System.out.println("=====================================================");
//        test1();

//        System.out.println("=====================================================");
//        test2();

//        System.out.println("=====================================================");
//        test3();

        System.out.println("=====================================================");
        test4();
    }

}
