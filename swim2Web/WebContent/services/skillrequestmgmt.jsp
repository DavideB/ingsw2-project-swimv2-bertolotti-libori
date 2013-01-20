<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%
ServletContext context = config.getServletContext();       
//ArrayList<String[]> resultlist = new ArrayList<String[]>();
//ArrayList<String> rl = new ArrayList<String>();
String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
String now = null;
Calendar cal = Calendar.getInstance();
SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servizi Admin - Gestione Richieste di Aggiunta Abilitá</title>
</head>
<body>
<div><a href="/swim2Web/index.jsp">Home</a></div>
<h1>Gestione Richieste di Aggiunta Abilitá</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", null); %>
	<%= session.getAttribute("email") %>
<h2>Elenco Richieste Ricevute</h2>	
<p>
Premi "Accetta" per registrare la nuova Abilitá o "Rifiuta" per rigettare la richiesta
</p>	
<table border>
		<tr>
			<td>Richiedente</td><td>Abilitá</td>
		</tr>
<%      
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/retrievenewskillrequests");
		dispatcher.include(request,response); 
		ArrayList<String[]> resultlist = (ArrayList<String[]>) session.getAttribute("resultlist");
		if ( resultlist != null && resultlist.size()>0 ) {
		for (int i=0; i<resultlist.size(); i++) {
%>
		<tr>		  
			<td><%=resultlist.get(i)[0]%></td>
			<td><%=resultlist.get(i)[1]%></td>
			<td><form action="/swim2Web/skillregistration" method="post">
				<input type="hidden" name="email" value="<%=resultlist.get(i)[0]%>" />
				<input type="hidden" name="skillname" value="<%=resultlist.get(i)[1]%>" />
				Messaggio di accettazione:<input type="text" name="adminansw" />
				<input type="submit" value="Accetta" />
				</form>
			</td>
			<td><form action="/swim2Web/skillrejection" method="post">
			Messaggio di rigetto:<input type="text" name="adminansw" />
				<input type="submit" value="Rifiuta" />
				</form>
			</td>
		</tr>
	<% } }%>
</table>
<p>
<table border>
	<tr>
		<td>				
<%      			
			RequestDispatcher disp = getServletContext().getRequestDispatcher("/retrieveskills");
			disp.include(request,response); 
			ArrayList<String> rl = (ArrayList<String>) session.getAttribute("resultlist");
			if ( rl != null && rl.size()>0 ) {
%>
			<table border><thead>Elenco degli Skill giá presenti:</thead>	
<%		
			for (int i=0; i<rl.size(); i++) {
%>
				<tr><td><%= rl.get(i) %></td></tr>
				 <% } 
					} %>
			</table>
		</td>
</table>        
</body>
</html>