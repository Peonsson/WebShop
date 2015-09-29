<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
    String userid = request.getParameter("uname");
    String pwd = request.getParameter("pass");
   if (pwd != null && userid != null)  {
	   session.setAttribute("userid", userid);
	}
%>

<h1>POST</h1>
<form action="${pageContext.request.contextPath}/UI/MyServlet" method="post" >
    <input type="submit" name="button1" value="Button 1" />
    <input type="submit" name="button2" value="Button 2" />
    <input type="submit" name="button3" value="Button 3" />
    
</form>

<h1>GET</h1>
<form action="${pageContext.request.contextPath}/UI/MyServlet" method="get">
    <input type="submit" name="button1" value="Button 1" />
    <input type="submit" name="button2" value="Button 2" />
    <input type="submit" name="button3" value="Button 3" />
</form>

</body>
</html>