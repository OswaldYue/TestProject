package com.mgw.controller;

import com.mgw.entity.MyBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

public class TestController {

	// http://localhost:8080/sayHello?age=12&name=Jack
	@RequestMapping("sayHello")
	@ResponseBody
	public ModelAndView sayHello(int age, String name) {
		ModelAndView mav = new ModelAndView();
		MyBean bean = new MyBean(age, name);
		mav.addObject("myBean", bean);
		mav.setViewName("sayHello");
		System.out.println(bean.toString());
		return mav;
	}
}
