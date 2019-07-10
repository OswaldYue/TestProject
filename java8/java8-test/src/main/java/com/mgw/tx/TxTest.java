package com.mgw.tx;

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


    public static void main(String[] args)  throws Exception{

//        System.out.println("=================================================");
//        test1();
        System.out.println("=================================================");
        test2();

    }
}
