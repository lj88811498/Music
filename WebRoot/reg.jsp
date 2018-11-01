<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="account.do" method="post">
		<input type="text" name="name" /> 
		<br/>
		<input type="password" name="password" /> 
		<input type="hidden" name="method"	value="reg" /> 
		<br/>
		<input type="submit" value="注册" />
	</form>
	<a href="login.jsp">登录</a>
</body>
</html>
