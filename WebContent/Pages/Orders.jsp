<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		%>

		<%
			if (session.getAttribute("loggedInUser") != null) {
		%><a class="logoutButton" href="/WebShop/logout">Logout</a>
		<%
			} else {
		%><a class="logoutButton" href="/WebShop/login">Login</a>
		<%
			}
		%>
	</div>

</body>
</html>