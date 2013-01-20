<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%
ServletContext context = config.getServletContext();       
List<String> resultlist = null;
String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
String now = null;
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servizi per Utenti Registrati</title>
</head>
<body>
<div><a href="/swim2Web/index.jsp">Home</a></div>
<div><a href="/swim2Web/logout.jsp">Logout</a></div>
<h1>Servizi per Utenti Registrati</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", null); %>
	Benvenuto <%= session.getAttribute("email") %>
<h2>Servizi disponibili:</h2>	
<div><a href="/swim2Web/services/skillrequest.jsp">Gestione Abilitá</a></div>

</body>
</html>