<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SWIMv2 Application</title>
</head>
<body>
	<div><a href="/swim2Web/index.jsp">Home</a></div>
	<h1>Welcome to the SWIMv2 Application</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", ""); %>
	<h2>Accesso Utente Registrato - Login</h2>
		<form action="/swim2Web/login" method="post">
		UserName: <input type="text" name="username" /><br />
		Password: <input type="text" name="password" /><br />
				  <input type="submit" value="Login" />
		</form>

</body>
</html>