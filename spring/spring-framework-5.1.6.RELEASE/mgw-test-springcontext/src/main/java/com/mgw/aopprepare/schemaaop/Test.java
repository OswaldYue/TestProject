package com.mgw.aopprepare.schemaaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void test1() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("schemaop.xml");
		Cat cat = ctx.getBean("cat", Cat.class);
		cat.sayHello("美美", 3);
	}


	public static void test2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("schemaop.xml");
		Cat cat = ctx.getBean("cat", Cat.class);
		cat.sayException("美美", 3);
	}

	public static void test3() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("v2/day06.xml");
		// 注意：getBean获取的是cat
		IIntroduce introduce = ctx.getBean("cat", IIntroduce.class);
		introduce.sayIntroduce();
	}

	public static void main(String[] args) {
//		test1();
//		test2();
		test3();
	}
}
