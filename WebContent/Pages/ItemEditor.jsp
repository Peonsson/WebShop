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

	<a href="/WebShop/ItemAdministrator">Go back</a>
	<form method="post" action="EditItem">
			Name: <input type="text" name="name" value="${item.name}">
			Price: <input type="text" name="price" value="${item.price}">
			Quantity: <input type="text" name="quantity" value="${item.quantity}">
			Category
			<select name="category">
				<option value="computer">Computer</option>
				<option value="food">Food</option>
				<option value="phone">Phone</option>
				<option value="tool">Tool</option>
				<option value="vehicle">Vehicle</option>
			</select>Remove<input name="remove" type="checkbox">
			<br />
			<input type="hidden" name="itemId" value="${item.itemId}"/>
			<input type="hidden" name="loggedInUser" value="<% out.print(session.getAttribute("loggedInUser")) ;%>"/>
			<button>Save changes</button>
	</form>
</body>
</html>