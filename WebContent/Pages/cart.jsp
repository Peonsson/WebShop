<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cart</title>
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
	
	<c:choose>
		<c:when test="${not empty items}">
			<table id="itemList">
				<tr>
					<td>Name</td>
					<td>Quantity</td>
					<td>Price</td>
				</tr>
				<c:forEach var="item" items="${items}">
				<tr>
					<td>${item.name}</td>
					<td>${item.quantity}</td>
					<td><c:out value="${item.price * item.quantity}"></c:out>(${item.price} each)</td>
					<td>
						<form method="post" action="RemoveItemFromCart">
							<input type="hidden" name="itemId" value="${item.itemId}"/>
							<button>Remove from cart</button>
						</form>
					</td>
					</tr>
				</c:forEach>
			</table>
			<form method="post" action="Checkout">
				<input type="hidden" name="loggedInUser" value="<%out.print(session.getAttribute("loggedInUser"));%>" />
				<button>Checkout order</button>
			</form>
		</c:when>
		<c:when test="${empty items}">
			Cart is empty!
		</c:when>
	</c:choose>
</body>
</html>