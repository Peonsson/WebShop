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

	Select order to edit
	<table>
		<c:forEach var="item" items="${items}">
			<tr>
				<td>${item.name}</td>
				<td>${item.quantity}</td>
				<td>${item.price}</td>
				<td>${item.itemId}</td>
				<td>
					<form method="post" action="ItemEditor?itemId=${item.itemId}">
						<button>Edit</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>