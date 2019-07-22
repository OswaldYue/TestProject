package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 1、告诉SpringMVC这是一个处理器，可以处理请求
 * 		@Controller：标识哪个组件是控制器
 * @author lfy
 *
 */
@Controller
public class MyFirstController {
	/**
	 * 	 @RequestMapping:告诉SpringMVC这个方法处理什么请求；
	 *   /代表从当前项目下开始；处理当前项目下的hello请求
	 */
	@RequestMapping("/hello")
	public String myfirstRequest(){
		System.out.println("请求收到了....正在处理中");
		//视图解析器自动拼串；
//		<property name="prefix" value="/WEB-INF/pages/"></property>
//		<property name="suffix" value=".jsp"></property>
		//   (前缀)/WEB-INF/pages/+返回值(success)+后缀（.jsp）
		return "success";
	}

}
