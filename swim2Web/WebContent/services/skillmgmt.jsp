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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servizi Admin - Gestione Abilitá</title>
</head>
<body>
<div><a href="/swim2Web/index.jsp">Home</a></div>
<h1>Gestione Abilitá</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", null); %>
	<%= session.getAttribute("email") %>
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