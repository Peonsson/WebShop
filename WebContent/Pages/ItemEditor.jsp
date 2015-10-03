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
	<div id="menu">
		<a class="menuButton" href="/WebShop/">Shop</a>
		<%
			if (session.getAttribute("loggedInUser") != null) {
		%>
		<a class="menuButton"
			href="/WebShop/cart?userId=<%out.print(session.getAttribute("loggedInUser"));%>">Cart</a>
		<a class="menuButton" href="/WebShop/Orders">Orders</a>
		<%
			}

			if ((session.getAttribute("accessLevel") != null)
				&& ((int) session.getAttribute("accessLevel") > 1)) {
		%>
		<a class="menuButton" href="/WebShop/Administration">Administration</a>
		<%
			}

			if (session.getAttribute("loggedInUser") != null) {
		%>
		<a class="menuButton" href="/WebShop/Settings">Settings</a> <a
			class="menuButton" href="/WebShop/logout">Logout</a>
		<%
			} else {
		%>
		<a class="menuButton" href="/WebShop/login">Login</a>
		<%
			}
		%>
	</div>

	<a href="/WebShop/Administration">Go back</a>
	<form method="post" action="EditItem">
		Name: <input type="text" name="name" value="${item.name}">
		Price: <input type="text" name="price" value="${item.price}">
		Quantity: <input type="text" name="quantity" value="${item.quantity}">
		Category <input type="text" name="category" value="${item.category}">
		Remove<input name="remove" type="checkbox"> <br /> <input
			type="hidden" name="itemId" value="${item.itemId}" /> <input
			type="hidden" name="loggedInUser"
			value="<%out.print(session.getAttribute("loggedInUser"));%>" />
		<button>Save changes</button>
	</form>
</body>
</html>