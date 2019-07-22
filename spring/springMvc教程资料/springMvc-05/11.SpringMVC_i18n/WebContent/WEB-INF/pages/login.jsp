<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>
<!-- 国际化的区域信息是决定国际化显示的因素 -->
	<fmt:message key="welcomeinfo"/>
</h1>
<form action="">
	<fmt:message key="username"/>:<input /><br/>
	<fmt:message key="password"/>:<input /><br/>
	<input type="submit" value="<fmt:message key="loginBtn"/>"/>
</form>
<!-- 如果点击超链接切换国际化 -->
<a href="tologinpage?locale=zh_CN">中文</a>|<a href="tologinpage?locale=en_US">English</a>
</body>
</html>