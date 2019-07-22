package com.atguigu.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

/**
 * 自定义视图
 * @author lfy
 *
 */
public class MyView implements View{

	/**
	 * 返回的数据的内容类型
	 */
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return "text/html";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("之前保存的数据："+model);
		response.setContentType("text/html");
		List<String> vn = (List<String>) model.get("video");
		response.getWriter().write("哈哈<h1>即将展现精彩内容</h1>");
		for (String string : vn) {
			response.getWriter().write("<a>下载"+string+".avi</a><br/>");
		}
		response.getWriter().write(""
				+ "<script>"
				+ "var aEle = document.getElementsByTagName('a');"
				+ "aEle.onclick=function(){"
				+ "alert('想下载吗？交学费')"
				+ "}"
				+ "</script>");
	}

}
