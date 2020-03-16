package com.mgw.txprepare;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void test1() {
		// 基于tx标签的声明式事物
		ApplicationContext ctx = new ClassPathXmlApplicationContext("txprepare.xml");
		IAccountService studentService = ctx.getBean("accountService", IAccountService.class);
		studentService.save();
	}

	// 嵌套事务
	public static void test2() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("txnest.xml");
		ITxNestBankService service = ctx.getBean("bankService", ITxNestBankService.class);
		service.save();
	}

	public static void main(String[] args) {

//		test1();
		test2();
	}
}
