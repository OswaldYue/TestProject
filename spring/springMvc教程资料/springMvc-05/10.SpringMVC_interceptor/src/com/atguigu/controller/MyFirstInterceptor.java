package com.atguigu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 1、实现HandlerInterceptor接口
 * 2、在SpringMVC配置文件中注册这个拦截器的工作；
 * 		配置这个拦截器来拦截哪些请求的目标方法；
 * 
 *
 */
public class MyFirstInterceptor implements HandlerInterceptor {

	/**
	 * 目标方法运行之前运行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("MyFirstInterceptor...preHandle...");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("MyFirstInterceptor...postHandle...");
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyFirstInterceptor...afterCompletion");
	}

}
