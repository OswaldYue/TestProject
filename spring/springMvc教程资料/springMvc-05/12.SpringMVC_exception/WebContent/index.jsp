<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	pageContext.setAttribute("ctp", request.getContextPath());
%>
</head>
<body>

<a href="${ctp }/handle01?i=10">test01-哈哈</a><br/>
<a href="${ctp }/handle02?username=admin">handle02</a><br/>
<a href="${ctp }/handle03">handle03</a><br/>
<a href="${ctp }/handle04">handle04</a>
</body>
</html>