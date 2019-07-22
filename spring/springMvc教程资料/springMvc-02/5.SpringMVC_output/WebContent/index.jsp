<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="hello">hello</a>
<!-- SpringMVC如何给页面携带数据过来； --><br/>
<a href="handle01">handle01</a><br/>
<a href="handle02">handle02</a><br/>
<a href="handle03">handle03</a><br/>
<a href="handle04">handle04</a>
<h1>修改图书-不能修改书名；</h1>
<form action="updateBook" method="post">
	<input type="hidden" name="id" value="100"/>
	书名：西游记；<br/>
	作者：<input type="text" name="author"/><br/>
	价格：<input type="text" name="price"/><br/>
	库存：<input type="text" name="stock"/><br/>
	销量：<input type="text" name="sales"/><br/>
	<input type="submit" value="修改图书"/>
</form>
</body>
</html>