package com.atguigu.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.LocaleResolver;

public class MyLocaleResolver implements LocaleResolver {

	/**
	 * 解析返回locale
	 */
	@Override
	public Locale resolveLocale(HttpServletRequest req) {
		// TODO Auto-generated method stub
		// zh_CN
		Locale l = null;
		String localeStr = req.getParameter("locale");
		//如果带了locale参数就用参数指定的区域信息，如果没带就用请求头的
		if (localeStr != null && !"".equals(localeStr)) {
			l = new Locale(localeStr.split("_")[0], localeStr.split("_")[1]);
		} else {
			l = req.getLocale();
		}
		return l;
	}

	/**
	 * 修改locale
	 */
	@Override
	public void setLocale(HttpServletRequest arg0, HttpServletResponse arg1,
			Locale arg2) {
		throw new UnsupportedOperationException(
				"Cannot change HTTP accept header - use a different locale resolution strategy");
	}

}
