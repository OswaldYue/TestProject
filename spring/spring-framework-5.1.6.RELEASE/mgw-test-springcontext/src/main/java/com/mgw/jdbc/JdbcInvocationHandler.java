package com.mgw.jdbc;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdbcInvocationHandler implements InvocationHandler {
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("deal db :" + method.getAnnotation(Select.class).value()[0]);

		return null;
	}
}
