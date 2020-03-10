package com.mgw.aopprepare.advice;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;

public class MyThrowsAdvice implements ThrowsAdvice {

	/**
	 * 异常增强
	 */
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) {
		System.out.println("==异常增强");
		System.out.println("==方法名：" + method.getName());
		if (null != args && args.length > 0) {
			for (int i = 0; i < args.length; i++) {
				System.out.println("==第" + (i + 1) + "参数：" + args[i]);
			}
		}
		System.out.println("==目标类信息：" + target.toString());
		System.out.println("==异常信息：" + ex.toString());
	}
}
