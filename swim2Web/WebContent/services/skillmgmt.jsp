<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%
ServletContext context = config.getServletContext();       
List<String> resultlist;
String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
String now = null;
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
%>
<%  String username = (String)session.getAttribute("username"); 
    String password = (String)session.getAttribute("password");
    String email = (String)session.getAttribute("email");
    Boolean isadmin = (Boolean)session.getAttribute("isadmin");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servizi Admin - Gestione Abilitá</title>
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
	
<h1>Gestione Abilitá</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", ""); %>
<h2>Inserimento Nuova Abilitá</h2>	
<p>

Inserisci i dati e clicca su "Insert":

</p>	
	<form action="/swim2Web/skillregistration" method="post">
		<input type="hidden" name="email" value="<%=session.getAttribute("email")%>" />
		<input type="hidden" name="adminansw" value="Unsolicited Skill created by Admin" /> 
		Skill Name:<input type="text" name="skillname" /> 
		<input type="submit" value="Insert" />
	</form>
<table border>
	<tr>
		<td>		
			
<%      
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/retrieveskills");
					dispatcher.include(request,response); 
					resultlist = (ArrayList<String>) session.getAttribute("resultlist");
					if ( resultlist != null && resultlist.size()>0 ) {
%>
			<table border><thead>Elenco degli Skill giá presenti:</thead>	
<%		
					for (int i=0; i<resultlist.size(); i++) {
%>
					<tr><td><%= resultlist.get(i) %></td></tr>
				 <% } 
					} %>
			</table>
		</td>
</table>
</body>
</html>