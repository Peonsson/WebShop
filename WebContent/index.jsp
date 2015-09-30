<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="stylesheets/styles.css">
<title>Web shop</title>
</head>
<body>
<% request.getSession();

if (session.getAttribute("loggedInUser") == null) {
	response.sendRedirect("login.jsp");
}

out.println("session user id = " + session.getAttribute("loggedInUser"));
%>

	<div class="container">
		<div id="menu">
			<a class="menuButton" href="index.jsp">Shop</a> <a class="menuButton"
				href="cart.jsp">Cart</a>
		</div>
		<div class="rightMenu">
			<a class="logoutButton" href="logout.jsp">Logout</a>
		</div>
		
		<div id="main">
			<table id="itemList">
				<jsp:include page="/ActionServlet" />
			</table>
		</div>
	</div>
</body>
</html>


