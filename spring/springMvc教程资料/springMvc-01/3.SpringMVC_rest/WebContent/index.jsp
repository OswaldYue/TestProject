<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 发起图书的增删改查请求；使用Rest风格的URL地址；
请求url	请求方式	表示含义
/book/1	GET：	查询1号图书
/book/1	DELETE：	删除1号图书
/book/1	PUT：	更新1号图书
/book	POST：	添加1号图书

从页面发起PUT、DELETE形式的请求?Spring提供了对Rest风格的支持
1）、SpringMVC中有一个Filter；他可以把普通的请求转化为规定形式的请求；配置这个filter;
	<filter>
		<filter-name>HiddenHttpMethodFilter</filter-name>
		<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>HiddenHttpMethodFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
2）、如何发其他形式请求？
	按照以下要求；1、创建一个post类型的表单 2、表单项中携带一个_method的参数，3、这个_method的值就是DELETE、PUT

 -->
<a href="book/1">查询图书</a><br/>
<form action="book" method="post">
	<input type="submit" value="添加1号图书"/>
</form><br/>


<!-- 发送DELETE请求 -->
<form action="book/1" method="post">
	<input name="_method" value="delete"/>
	<input type="submit" value="删除1号图书"/>
</form><br/>

<!-- 发送PUT请求 -->
<form action="book/1" method="post">
	<input name="_method" value="put"/>
	<input type="submit" value="更新1号图书"/>
</form><br/>
</body>
</html>