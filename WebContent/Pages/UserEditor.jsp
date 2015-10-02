<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% out.print(session.getAttribute("loggedInUser")); %>

	<a href="/WebShop/Administration">Go back</a>
	<form method="post" action="EditUser">
			Name: <input type="text" name="name" value="${user.username}">
			Password: <input type="text" name="password" value="${user.password}">
			Access Level: <input type="text" name="accessLevel" value="${user.accessLevel}">
			<br />
			<input type="hidden" name="userId" value="${user.userId}"/>
			<input type="hidden" name="loggedInUser" value="<% out.print(session.getAttribute("loggedInUser")) ;%>"/>
			<button>Save changes</button>
	</form>
</body>
</html>