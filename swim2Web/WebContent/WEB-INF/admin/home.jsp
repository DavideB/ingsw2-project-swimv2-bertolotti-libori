<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page contentType="text/html; charset=UTF-8" />
<jsp:directive.page import="it.polimi.swim2.persistence.*" />
<!-- jsp:directive.page import="org.displaytag.*" /-->
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<%  String username = (String)session.getAttribute("username"); 
    String password = (String)session.getAttribute("password");
    String email = (String)session.getAttribute("email");
    Boolean isadmin = (Boolean)session.getAttribute("isadmin");
%>
<html>
<head>
<title>Insert title here</title>
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
	<div>
		<a href="WEB-INF/admin/skillmgmt.jsp">Gestione Abilitá</a>
	</div>
	<div>
		<a href="WEB-INF/admin/skillrequestmgmt.jsp">Gestione Richieste Aggiunta Abilitá</a>
	</div>
	<div>
		<a href="/swim2Web/services/skillmgmt.jsp">Gestione Abilitá in /services/...</a>
	</div>
	<div>
		<a href="/swim2Web/services/skillrequestmgmt.jsp">Gestione Richieste Aggiunta Abilitá in /services/...</a>
	</div>
</body>
</html>