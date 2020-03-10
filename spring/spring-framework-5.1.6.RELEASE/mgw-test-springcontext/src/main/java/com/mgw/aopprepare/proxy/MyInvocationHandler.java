package com.mgw.aopprepare.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

	// 目标对象
	private Object target;

	/**
	 * 构造方法
	 * @param target 目标对象
	 */
	public MyInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	/**
	 * @param proxy  JDK动态生成的最终代理对象
	 * @param method 调用真实对象的某个方法的Method对象
	 * @param args   调用真实对象某个方法时接受的参数
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("==代理方法开始执行");
		Object invoke = method.invoke(target, args);
		System.out.println("==代理方法结束执行");
		return invoke;
	}

	/**
	 * 获取目标对象的代理对象
	 * @return 代理对象
	 */
	public Object getProxy() {
		/**
		 * 参数：
		 * 1、获取当前线程的类加载
		 * 2、获取接口
		 * 3、当前对象
		 */
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
				target.getClass().getInterfaces(),
				this);
	}
}
