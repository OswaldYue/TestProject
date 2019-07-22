package com.atguigu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExceptionTestController {

	// @ResponseStatus(reason="反正我错误了。。。",value=HttpStatus.NOT_EXTENDED)
	@RequestMapping("/handle01")
	public String handle01(Integer i) {
		System.out.println("handle01....");
		System.out.println(10 / i);
		return "success";
	}

	@RequestMapping("/handle02")
	public String handle02(@RequestParam("username") String username) {
		if (!"admin".equals(username)) {
			
			System.out.println("登陆失败....");
			throw new UserNameNotFoundException();
		}
		System.out.println("登陆成功！。。。");
		return "success";
	}
	
	@RequestMapping(value="/handle03",method=RequestMethod.POST)
	public String handle03(){
		return "success";
	}
	
	@RequestMapping("/handle04")
	public String handle04(){
		System.out.println("handle04");
		String str = null;
		System.out.println(str.charAt(0));
		return "success";
	}

	/**
	 * 告诉SpringMVC这个方法专门处理这个类发生的异常 1、给方法上随便写一个Exception，用来接受发生的异常
	 * 2、要携带异常信息不能给参数位置写Model； 3、返回ModelAndView就行了；
	 * 4、如果有多个@ExceptionHandler都能处理这个异常，精确优先 5、全局异常处理与本类同时存在，本类优先；
	 */
//	@ExceptionHandler(value = { Exception.class })
//	public ModelAndView handleException01(Exception exception) {
//		System.out.println("本类的：handleException01..." + exception);
//		//
//		ModelAndView view = new ModelAndView("myerror");
//		view.addObject("ex", exception);
//		// 视图解析器拼串
//		return view;
//	}

}
