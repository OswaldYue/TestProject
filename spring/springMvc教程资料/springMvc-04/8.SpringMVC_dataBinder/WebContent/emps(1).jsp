<%@page import="java.util.Date"%>
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
<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
</head>
<body>
<%=new Date() %>
<a href="${ctp }/getallajax">ajax获取所有员工</a><br/>

<div>

</div>
<script type="text/javascript">
	$("a:first").click(function(){
		//1、发送ajax获取所有员工上
		$.ajax({
			url:"${ctp}/getallajax",
			type:"GET",
			success:function(data){
				//console.log(data);
				$.each(data,function(){
					var empInfo = this.lastName+"-->"+this.birth+"--->"+this.gender;
					$("div").append(empInfo+"<br/>");
				});
			}
		});
		
		return false;
	});
</script>
</body>
</html>