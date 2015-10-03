<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
if (session.getAttribute("loggedInUser") != null) {
	response.sendRedirect("/WebShop/browse");
}
%>
	<form method="post" action="LoginService">
		Username
		<input type="text" name="username" value="" />
		Password
		<input type="password" name="password" value="" />
		<input type="submit" value="Login" />
	</form>
</body>
</html>