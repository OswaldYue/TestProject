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
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
<body>
	<form action="${ctp }/test02" method="post"
		enctype="multipart/form-data">
		<input name="username" value="tomcat" /> <input name="password"
			value="123456"> <input type="file" name="file" /> <input
			type="submit" />
	</form>
	<a href="${ctp }/testRequestBody">ajax发送json数据</a>
</body>
<script type="text/javascript">
	$("a:first").click(function() {
		//点击发送ajax请求，请求带的数据是json
		var emp = {
			lastName : "张三",
			email : "aaa@aa.com",
			gender : 0
		};
		//alert(typeof emp);
		//js对象
		var empStr = JSON.stringify(emp);
		//alert(typeof empStr);
		$.ajax({
			url : '${ctp}/testRequestBody',
			type : "POST",
			data : empStr,
			contentType : "application/json",
			success : function(data) {
				alert(data);
			}
		});
		return false;
	});
</script>
</html>