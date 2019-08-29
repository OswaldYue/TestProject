package com.mgw.tx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TxTest {



    public static void test1() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        System.out.println(jdbcTemplate);


    }

    public static void test2() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        BookService bookService = context.getBean(BookService.class);

        bookService.checkout("Tom","ISBN-01");

    }

    /**
     * 有事务的业务逻辑,容器中保存的是这个业务逻辑的代理对象
     * */
    public static void test3() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        BookService bookService = context.getBean(BookService.class);

        //bookService.checkout2("Jerry","ISBN-02");

        //class com.mgw.tx.BookService$$EnhancerBySpringCGLIB$$36dcc24f
        System.out.println(bookService.getClass());
    }


    public static void test4() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        MulService mulService = context.getBean(MulService.class);

        mulService.mulTx();

    }

    public static void test5() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        BookService bookService = context.getBean(BookService.class);
        //实际会回滚
        bookService.mulTx();
        /*
        * 如果是mulService.mulTx()则不会回滚,会数据更新成功
        * 原因:
        * MulServiceProxy.mulTx() {
        *
        *   BookServiceProxy.checkout2();
        *   BookServiceProxy.updatePrice();
        *
        * }
        * 全部都是代理对象调用方法
        *
        * 而bookService.mulTx()会回滚,数据更新不成功
        * 原因:
        * BookServiceProxy.mulTx() {
        *   checkout2();
        *   updatePrice();
        * }
        * 直接调用的方法,而不是代理对象调用,所以没有事务
        *
        *
        * */
    }

    public static void test6() {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");

        BookServiceXml bookServiceXml = context.getBean(BookServiceXml.class);

        bookServiceXml.checkout("Tom","ISBN-01");
    }

    public static void test7() {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);

        BookServiceXml bookServiceXml = context.getBean(BookServiceXml.class);

        bookServiceXml.checkout("Tom","ISBN-01");
    }


    public static void main(String[] args)  throws Exception{

//        System.out.println("=================================================");
//        test1();
//        System.out.println("=================================================");
//        test2();
//        System.out.println("=================================================");
//        test3();
//        System.out.println("=================================================");
//        test4();
//        System.out.println("=================================================");
//        test5();
//        System.out.println("=================================================");
//        test6();

        System.out.println("=================================================");
        test7();
    }
}
