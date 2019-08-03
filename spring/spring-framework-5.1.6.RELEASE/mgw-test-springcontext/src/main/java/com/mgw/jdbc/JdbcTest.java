package com.mgw.jdbc;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Proxy;

public class JdbcTest {

	/**
	 * 测试数据源 mybatis快速使用spring的过程 参考:http://www.mybatis.org/spring/getting-started.html
	 * compile group: 'org.mybatis', name: 'mybatis-spring', version: '2.0.2'
	 * */
	public static void test1() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		SqlSessionFactoryBean sqlSessionFactoryBean = context.getBean(SqlSessionFactoryBean.class);

		System.out.println(sqlSessionFactoryBean);
	}
	/**
	 * 测试数据库获取数据
	 * */
	public static void test2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		AccountDao accountDao = context.getBean(AccountDao.class);
//		System.out.println(accountDao.getClass());
		AccountEntity mgw = accountDao.getAccountByUsername("Jerry");

		System.out.println(mgw);

	}

	/**
	 * 使用代理模拟mybatis处理sql
	 *
	 * */
	public static void test3() {

		//mybatis源码:Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
		AccountMapper AccountMapper = (AccountMapper)Proxy.newProxyInstance(JdbcTest.class.getClassLoader(), new Class[]{AccountMapper.class}, new JdbcInvocationHandler());
		AccountMapper.getAccountByUsername("xxx");
		System.out.println(AccountMapper.getClass());
	}

	/**
	 * mybatis底层知识  参考:http://www.mybatis.org/mybatis-3/zh/getting-started.html
	 * */
	public static void test4() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);


		SqlSession sqlSession = null;
		//可跟踪代码 就可以查看到底层使用的就是代理
		AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
		accountMapper.getAccountByUsername("xxx");

	}

	/**
	 * 如果将一个自己已经创建好的对象加入到spring中?
	 * 1. @Bean可以
	 * 2. context.getBeanFactory().registerSingleton()
	 * 3. 加入到容器中的FactoryBean的实现类   例如:MyFactoryBean
	 * */
	public static void test5() {

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		//context.getBeanFactory().registerSingleton("xxx",new Object());

//		TestBean1是在MyFactoryBean中的
//		class com.mgw.jdbc.TestBean1
//		com.mgw.jdbc.TestBean1@5f282abb
		TestBean1 testBean1 = context.getBean(TestBean1.class);
		System.out.println(testBean1.getClass());
		System.out.println(testBean1);

//		class com.mgw.jdbc.TestBean1
//		com.mgw.jdbc.TestBean1@5f282abb
		Object myFactoryBean = context.getBean("myFactoryBean");
		System.out.println(myFactoryBean.getClass());
		System.out.println(myFactoryBean);

//		class com.mgw.jdbc.MyFactoryBean
//		com.mgw.jdbc.MyFactoryBean@167fdd33
		Object myFactoryBean2 = context.getBean("&myFactoryBean");
		System.out.println(myFactoryBean2.getClass());
		System.out.println(myFactoryBean2);

		//测试前需要先将JdbcConfig配置类的@MapperScan("com.mgw.jdbc")注解与@MyMapperScan去掉 让其使用ProxyFactoryBean1中的自己产生的代理对象
		AccountMapper accountMapper = context.getBean(AccountMapper.class);
		accountMapper.getAccountByUsername("xxx");
		System.out.println(accountMapper.getClass());
		System.out.println(accountMapper);//此处为何会报错?暂时没查到
	}

	/**
	 * 测试使用ProxyFactoryBean类来生成Mapper的代理对象 必须为JdbcConfig配置类加上自定义的@MyMapperScan注解以及ProxyFactoryBean1上不能加@Component注解 保证容器中只有一个AccountMapper代理对象
	 *
	 *
	 * */
	public static void test6() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JdbcConfig.class);
		AccountMapper accountMapper = context.getBean(AccountMapper.class);
		accountMapper.getAccountByUsername("xxx");
	}

	public static void main(String[] args) {

//		System.out.println("----------------------------------------");
//		test1();

//		System.out.println("----------------------------------------");
//		test2();

//		System.out.println("----------------------------------------");
//		test3();

//		System.out.println("----------------------------------------");
//		test4();

//		System.out.println("----------------------------------------");
//		test5();

		System.out.println("----------------------------------------");
		test6();
	}
}
