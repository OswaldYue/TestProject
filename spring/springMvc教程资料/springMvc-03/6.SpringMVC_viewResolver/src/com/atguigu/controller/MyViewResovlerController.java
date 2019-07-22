package com.atguigu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义视图解析器和视图对象；
 * @author lfy
 *
 */
@Controller
public class MyViewResovlerController {
	
	@RequestMapping("/handleplus")
	public String handleplus(Model model){
		//meinv:/gaoqing  meinv:/dama
		//forward:/login.jsp
		List<String> vname = new ArrayList<String>();
		List<String> imgname = new ArrayList<String>();
		vname.add("佟老师");
		vname.add("飞哥");
		imgname.add("萌萌");
		
		model.addAttribute("video", vname);
		model.addAttribute("imgs", imgname);
		
		return "meinv:/gaoqing";
	}

}
