package com.mgw.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 在 Servlet API 中有一个 ServletContextListener 接口，它能够监听 ServletContext 对象的生命周期，
 * 实际上就是监听 Web 应用的生命周期。当Servlet 容器启动或终止Web 应用时，会触发ServletContextEvent 事件，
 * 该事件由ServletContextListener 来处理。在 ServletContextListener 接口中定义了处理ServletContextEvent 事件的两个方法
 * */
public class MyServletContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("MyServletContextListener init...");
		ServletContext servletContext = sce.getServletContext();
		//重新将此二值赋值
		servletContext.setAttribute("name","Jacks");
		servletContext.setAttribute("age","23");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("MyServletContextListener destroyed...");
	}
}