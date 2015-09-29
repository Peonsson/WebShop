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


<div class="container">
	<div id="menu">
		  <a class="menuButton" href="index.jsp">Shop</a>
		  <a class="menuButton" href="cart.jsp">Cart</a>
<!-- 		  <div class="rightMenu"> -->
		  	<a class="logoutButton" href="logout.jsp">Logout</a>
<!-- 		  </div> -->
	</div>
	<div id="main">
			<table id="itemList">
				<tr>
					<td>Item</td>
					<td>Quantity</td>
					<td>Price</td>
				</tr>
				<% for (int i = 0; i < 5; i++) {
					out.println("<tr><td>Item " + (i + 1) + "</td><td>" + i + "</td><td>" + i + "</td><td><button onClick=\"doStuff()\">Button</button></td></tr>");
				}%>
			</table>		
	</div>
</div>



<script>
	function doStuff() {
		document.write("hello");
	}
</script>

</body>
</html>


