package com.mgw.ioc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AA  {

	/*
	* 1.当autowireService1没有任何注解,只有get,set方法时,此时autowireService1时不会被注入
	*
	* 2.当autowireService1有@Autowired注解时,会被自动注入(com.mgw.ioc.AutowireService1@24273305),此时不需要提供get与set方法 因为默认的注入技术是:AUTOWIRE_NO
	* 提供@Autowired注解时会先根据Type来找,再根据name来找,装配是使用反射的filed.set(值,对象) 还是使用后置处理器InstantiationAwareBeanPostProcessor.postProcessPropertyValues()
	*
	* 3.当成员变量autowireService1不加@Autowired注解时,提供set方法时,若修改MyBeanFactoryPostProcessor中设置beanDefinition.setAutowireMode(1)的注入技术是1(AUTOWIRE_BY_NAME)时,也可以自定注入
	* 此时是spring的writeMethod在起作用(writeMethod的规则,方法必须set开头,方法参数必须在spring容器中,否则找不到)
	*
	* 4.当没有autowireService1这个成员变量,当有setAutowireService1(AutowireService1 autowireService1)这个方法时,
	* 且MyBeanFactoryPostProcessor中设置beanDefinition.setAutowireMode(1)的注入技术是1(AUTOWIRE_BY_NAME)时,见测试3,依然会为参数注入autowireService1
	* 此时使用method.invoke()进行反射  其实情形3与情形4原理是一样的
	*
	* 5.当没有autowireService1这个成员变量,当有setAutowireService1(AutowireService2 autowireService2)这个方法时,
	* 且MyBeanFactoryPostProcessor中设置beanDefinition.setAutowireMode(1)的注入技术是1(AUTOWIRE_BY_NAME)时,见测试4,此时会出错
	* 出错信息:Cannot convert value of type 'com.mgw.ioc.AutowireService1' to required type 'com.mgw.ioc.AutowireService2' for property 'autowireService1': no matching editors or conversion strategy found
	*
	* 6.当没有autowireService1这个成员变量,当有测试3或者测试4时,且MyBeanFactoryPostProcessor中设置beanDefinition.setAutowireMode(2)的注入技术是2(AUTOWIRE_BY_TYPE)时,
	* 测试3,测试4都是可以正常运行,并注入相应的变量.但是当测试3和测试4同时存在时,则只会根据重载方法前后调用第一个方法,并给出警告信息:
	* 警告: Invalid JavaBean property 'autowireService1' being accessed! Ambiguous write methods found next to actually used [public void com.mgw.ioc.AA.setAutowireService1(com.mgw.ioc.AutowireService2)]: [public void com.mgw.ioc.AA.setAutowireService1(com.mgw.ioc.AutowireService1)]
	*
	*
	* 结论1:通过情形3,4,5可以得到当设置注入方式是AUTOWIRE_BY_NAME时,有set方法且方法参数在spring容器中时,不管有没有相应的成员变量(autowireService1),spring都会尝试去注入方法参数,
	* 但注入参数的类型只会是根据setXXX()的XXX首字母小写去容器中找相应的beanName,而不是根据具体参数的名称或者类型去找
	*
	* 结论2:通过情形3,4,5,6可以得到当设置注入方式是AUTOWIRE_BY_TYPE时,有set方法且方法参数在spring容器中时,不管有没有相应的成员变量(autowireService1),spring都会尝试去注入方法参数,
	* 但注入参数会根据具体的参数类型去找
	* */
//	@Autowired
//	AutowireService1 autowireService1;

//	public void setAutowireService1(AutowireService1 autowireService1) {
//		this.autowireService1 = autowireService1;
//	}
//
//	public AutowireService1 getAutowireService1() {
//		return autowireService1;
//	}

	/**
	 * 测试3
	 * 输出"没有autowireService1的这个成员变量:com.mgw.ioc.AutowireService1@44e81672"
	 * */
	public void setAutowireService1(AutowireService1 autowireService1) {

		System.out.println("没有autowireService1的这个成员变量:" + autowireService1);
	}

	/**
	 * 测试4
	 *
	 * */
//	public void setAutowireService1(AutowireService2 autowireService2) {
//
//		System.out.println("没有autowireService1的这个成员变量:" + autowireService2);
//	}

	/*
	* spring的注入方式:byName  byType
	* spring的注入技术(模型):AUTOWIRE_NO AUTOWIRE_BY_NAME AUTOWIRE_BY_TYPE AUTOWIRE_CONSTRUCTOR
	*
	* */
	@PostConstruct
	public void initAA() {

		System.out.println("---AA---initAA()---Lifecycle Callback---");

	}
	/*
	* 当在MyBeanFactoryPostProcessor中设置beanDefinition.setAutowireMode(1)注入模型是AUTOWIRE_NO时
	* 则会去找默认的构造方法
	* */
	public AA(){
		System.out.println("---AA---AA()---");
	}

	public AA(String name) {
		System.out.println("---AA---AA(String name)---");
	}

	public AA(AutowireService1 autowireService1) {
		System.out.println("---AA---AA(AutowireService1 autowireService1)---");
	}

	/*
	 * 当在MyBeanFactoryPostProcessor中设置beanDefinition.setAutowireMode(3)注入技术是AUTOWIRE_CONSTRUCTOR时
	 * 由调用AA()构造方法变成了调用AA(AutowireService1 autowireService1,AutowireService2 autowireService2)构造方法
	 * 原理是:注入技术是AUTOWIRE_CONSTRUCTOR时则spring会去找参数在spring容器中最多的那个构造方法,不是参数最多,而是参数在容器中最多的那个,因为有些参数可能不在容器中
	 * */
	public AA(AutowireService1 autowireService1,AutowireService2 autowireService2) {
		System.out.println("---AA---AA(AutowireService1 autowireService1,AutowireService2 autowireService2)---");
	}

	private AA(AutowireService1 autowireService1,AutowireService2 autowireService2,AutowireService3 autowireService3) {
		System.out.println("---AA---AA(AutowireService1 autowireService1,AutowireService2 autowireService2,AutowireService3 autowireService3)---");
	}

}
