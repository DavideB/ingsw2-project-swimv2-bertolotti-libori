<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,java.text.*,java.net.*;"%>

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
	<h2>Test Page - list users and requests for help</h2>
		<a href="/swim2Web/listusers">Elenca tutti gli utenti</a><br>
		<a href="/swim2Web/listhelprequests">Elenca tutte le richieste di aiuto</a><br>
		<a href="/swim2Web/listnewskillrequests">Elenca tutte le richieste di aggiunta di nuove abilitá</a>
	<%= session.getAttribute("out") %>
	<% session.setAttribute("out", null); %>
	<h2>Test Page - insert a new user</h2>
		<form action="/swim2Web/test" method="post">
		E-mail: <input type="text" name="email" /><br />
		Name: <input type="text" name="name" /><br />
		Surname: <input type="text" name="surname" /><br />
		Password: <input type="text" name="password" /><br />
		<input type="submit" value="Enter" />
	</form>
</body>
</html>