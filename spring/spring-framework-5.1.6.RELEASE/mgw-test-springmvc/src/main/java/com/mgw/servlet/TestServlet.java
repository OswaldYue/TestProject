package com.mgw.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet extends HttpServlet {

	private String message;

	@Override
	public void init() throws ServletException {
		// 执行必需的初始化
		message = "Hello World";
		System.out.println("==TestServlet init");
	}

	@Override
	public void destroy() {
		System.out.println("==TestServlet destroy");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 注意这儿的代码，跟下面讲的ServletContextListener有关联
		ServletContext servletContext = getServletContext();
		String name = servletContext.getAttribute("name").toString();
		String age = servletContext.getAttribute("age").toString();
		// 设置响应内容类型
		resp.setContentType("text/html");

		// 实际的逻辑是在这里
		PrintWriter out = resp.getWriter();
		out.println("<h1>" + name + age + "</h1>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置响应内容类型
		resp.setContentType("text/html");

		// 实际的逻辑是在这里
		PrintWriter out = resp.getWriter();
		out.println("<h1>" + message + "</h1>");
	}
}
