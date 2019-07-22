package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 	@RequestMapping模糊匹配功能
 * 
 * @author lfy
 * 
 * URL地址可以写模糊的通配符：
 * 	？：能替代任意一个字符
 * 	*：能替代任意多个字符，和一层路径
 * 	**：能替代多层路径
 *
 */
@Controller
public class RequestMappingTest {
	
	@RequestMapping("/antTest01")
	public String antTest01(){
		System.out.println("antTest01...");
		return "success";
	}
	
	/**
	 * ?匹配一个字符,0个多个都不行;
	 * 		模糊和精确多个匹配情况下，精确优先
	 * 
	 * @return
	 */
	@RequestMapping("/antTest0?")
	public String antTest02(){
		System.out.println("antTest02...");
		return "success";
	}
	
	/**
	 *   *匹配任意多个字符
	 * @return
	 */
	@RequestMapping("/antTest0*")
	public String antTest03(){
		System.out.println("antTest03...");
		return "success";
	}
	
	/**
	 *  *：匹配一层路径
	 * @return
	 */
	@RequestMapping("/a/*/antTest01")
	public String antTest04(){
		System.out.println("antTest04...");
		return "success";
	}
	
	@RequestMapping("/a/**/antTest01")
	public String antTest05(){
		System.out.println("antTest05...");
		return "success";
	}
	
	//路径上可以有占位符：  占位符 语法就是可以在任意路径的地方写一个{变量名}
	//   /user/admin    /user/leifengyang
	// 路径上的占位符只能占一层路径
	@RequestMapping("/user/{id}")
	public String pathVariableTest(@PathVariable("id")String id){
		System.out.println("路径上的占位符的值"+id);
		return "success";
	}

}
