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
	<a href="/WebShop/ItemAdministrator">Go back</a>
	<form>
			Name: <input type="text" value="${item.name}">
			Price: <input type="text" value="${item.price}">
			Quantity: <input type="text" value="${item.quantity}">
			Category
			<select>
				<option value="computer">Computer</option>
				<option value="food">Food</option>
				<option value="phone">Phone</option>
				<option value="tool">Tool</option>
				<option value="vehicle">Vehicle</option>
			</select>Remove<input type="checkbox">
			<br />
			<button>Save changes</button>
	</form>
</body>
</html>