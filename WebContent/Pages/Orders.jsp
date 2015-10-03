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
		<a class="menuButton"
			href="/WebShop/Orders?userId=<%out.print(session.getAttribute("loggedInUser"));%>">Orders</a>
		<%
			}
			if (session.getAttribute("loggedInUser") != null) {
		%><a class="logoutButton" href="/WebShop/logout">Logout</a>
		<%
			} else {
		%><a class="logoutButton" href="/WebShop/login">Login</a>
		<%
			}
		%>
	</div>

	<c:forEach var="order" items="${orders}">
		Order (<c:choose>
			<c:when test="${order.sent == 1}">Sent</c:when>
			<c:when test="${order.sent == 0}">Not sent</c:when>
		</c:choose>):
		<table>
			<tr>
				<td>Name</td>
				<td>Price</td>
				<td>Quantity</td>
				<td>Category</td>
			</tr>
			<c:forEach var="item" items="${order.items}">
				<tr>
					<td><c:out value="${item.name}"></c:out></td>
					<td><c:out value="${item.price}"></c:out></td>
					<td><c:out value="${item.quantity}"></c:out></td>
					<td><c:out value="${item.category}"></c:out></td>
				</tr>
			</c:forEach>

		</table>
	</c:forEach>
</body>
</html>