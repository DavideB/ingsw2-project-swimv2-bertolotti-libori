<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,java.util.*,java.text.*,java.net.*;"%>

<%  String username = (String)session.getAttribute("username"); 
    String password = (String)session.getAttribute("password");
    String email = (String)session.getAttribute("email");
    Boolean isadmin = (Boolean)session.getAttribute("isadmin");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SWIMv2 Application</title>
</head>
<body>
	<div>Utente<%if ( username != null ) { %> (registrato): <%= username %> <% } 
				 else if ( email!=null ) { %> (non registrato): <%= email %> <% } 
					  else { %>: nessun utente <% } %></div>
	<table> 
		<tr>	
			<td><a href="/swim2Web/index.jsp">Home   </a></td>
			<%if ( username == null && email!=null) {%>
			<td><a href="/swim2Web/services/nreg.jsp">Servizi Utenti Non Registrati   </a></td>
			<% } %>
			<%if ( username != null && email!=null) {
				if ( isadmin ) { %>
					<td><a href="WEB-INF/admin/home.jsp">Servizi Admin   </a></td>
				<% }
				else {%>
					<td><a href="/swim2Web/services/reg.jsp">Servizi Utenti Registrati   </a></td>
				<% } %>
			<td><a href="/swim2Web/logout">Logout   </a></td>
			<% } %>			
			<td><a href="/swim2Web/access/test.jsp">Test Page   </a></td>
		</tr>
	</table>

	<h1>Welcome to the SWIMv2 Application</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", ""); %>
	<h2>Test Page - list users and requests for help</h2>
		<a href="/swim2Web/listusers">Elenca tutti gli utenti</a><br>
		<a href="/swim2Web/listskills">Elenca tutti gli Skills presenti</a><br>
		<a href="/swim2Web/listhelprequests">Elenca tutte le richieste di aiuto</a><br>
		<a href="/swim2Web/listnewskillrequests">Elenca tutte le richieste di aggiunta di nuove abilitá</a>

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