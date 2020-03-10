package com.mgw.aopprepare.aspectjaop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 切面类
 * */
@Aspect
public class DogAspect {
	/**
	 * 例如：execution (* com.sample.service.impl..*.*(..)
	 * 1、execution(): 表达式主体。
	 * 2、第一个*号：表示返回类型，*号表示所有的类型。
	 * 3、包名：表示需要拦截的包名，后面的两个点表示当前包和当前包的所有子包，
	 * 即com.sample.service.impl包、子孙包下所有类的方法。
	 * 4、第二个*号：表示类名，*号表示所有的类。
	 * 5、*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个点表示任何参数。
	 **/

	@Pointcut("execution(* com.mgw.aopprepare.aspectjaop..*.*(..))")
	public void test() {

	}

	@Before("test()")
	public void beforeTest() {
		System.out.println("==前置增强");
	}

	@After("test()")
	public void afterTest() {
		System.out.println("==后置最终增强");
	}

	@AfterThrowing("test()")
	public void afterThrowingTest() {
		System.out.println("==后置异常增强");
	}

	@AfterReturning("test()")
	public void afterReturningTest() {
		System.out.println("==后置返回增强");
	}

	@Around("test()")
	public Object aroundTest(ProceedingJoinPoint p) {
		System.out.println("==环绕增强开始");
		Object o = null;
		try {
			o = p.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("==环绕增强结束");
		return o;
	}

	@DeclareParents(value = "com.mgw.aopprepare.aspectjaop.Dog", defaultImpl = IntroduceImpl.class)
	private IIntroduce iIntroduce;
}
