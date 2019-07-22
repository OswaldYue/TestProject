package com.atguigu.view;

import java.util.Locale;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class MyMeiNVViewResolver implements ViewResolver,Ordered{

	private Integer order = 0;
	
	@Override
	public View resolveViewName(String viewName, Locale locale)
			throws Exception {
		// TODO Auto-generated method stub
		//根据视图名返回视图对象
		/**
		 * 	meinv:/gaoqing  meinv:/dama
			forward:/login.jsp
		 */
		if(viewName.startsWith("meinv:")){
			return new MyView();
		}else{
			//如果不能处理返回null即可
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
	public int getOrder() {
		// TODO Auto-generated method stub
		return order;
	}
	
	//改变视图解析器的优先级
	public void setOrder(Integer order){
		this.order = order;
	}

}
