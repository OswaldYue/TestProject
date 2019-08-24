package com.mgw.log;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试spring4与spring5日志的区别
 *
 * */
public class LogTest {



	public static void test1() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LogConfig.class);

		context.start();

	}


	public static void main(String[] args) {

		System.out.println("===========================================================");
		test1();



	}

}
