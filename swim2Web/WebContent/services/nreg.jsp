<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.text.*"%>
<%
	ServletContext context = config.getServletContext();       
	List<String> resultlist = null;
	String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
	String now = null;
	Calendar cal = Calendar.getInstance();
	SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	String username = (String)session.getAttribute("username"); 
    String password = (String)session.getAttribute("password");
    String email = (String)session.getAttribute("email");
	Boolean isadmin = (Boolean)session.getAttribute("isadmin");
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servizi per Utenti Non Registrati</title>
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

<h1>Servizi per Utenti Non Registrati</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", ""); %>
<h2>Servizio di Registrazione</h2>	
<p>

Per registrarti, riempi i campi con i tuoi dati e clicca su "Register":

</p>	
	<form action="/swim2Web/userregistration" method="post">
		<input type="hidden" name="email" value="<%= session.getAttribute("email") %>" />
		Password: <input type="password" name="password" /><br />
		First Name: <input type="text" name="firstname" /><br />
		Surname: <input type="text" name="lastname" /><br />
		Birthdate: 
		<table border>
			<tr>
				<td>
					<label>day</label>
					<select name="day" id="day">
					<%for (int d = 1; d <= 31; d++) { 
						String dd;
						if (d<10) {dd = "0" + Integer.toString(d);}
						else {dd = Integer.toString(d);} %>
						<option value=<%= dd %>><%= dd %></option>
						<% } %>
					</select>
				</td>
				<td>
					<label>month</label>
					<select name="month" id="month">
					<%for (int m = 1; m <= 12; m++) {
						String mm;
						if (m<10){mm = "0" + Integer.toString(m);}
						else {mm = Integer.toString(m);} %>
						<option value=<%= mm %>><%= mm %></option>
						<% } %>
					</select>
				</td>
				<td>
					<label>year</label>
					<select name="year" id="year">
					<%for (int y = 1900; y <= 2013; y++) { 
						String yyyy = Integer.toString(y);%>
						<option value=<%= yyyy %>><%= yyyy %></option>
						<% ;} %>
					</select>
				</td>
				<td>
					<input type="submit" value="Register" />
				</td>
			</tr>
		</table>		
		</form>	
<h2>Creazione Richieste di Aiuto</h2>
<table border>
	<tr>
		<td>
			<table border><thead>Elenco degli Skill disponibili:</thead>
			<%      
		 	//response.sendRedirect(response.encodeRedirectURL("/listskills"));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/retrieveskills");
			dispatcher.include(request,response); 
			resultlist = (ArrayList<String>) session.getAttribute("resultlist");
			if ( resultlist != null && resultlist.size()>0 ) {
			for (int i=0; i<resultlist.size(); i++) {
			%>
			<tr><td><%= resultlist.get(i) %></td></tr>
			<% } }%>
			</table>
		</td>
		<td>
			<table border><thead>Crea la tua richiesta</thead>
			<tr><td>
				<form action="/swim2Web/helprequestcreation" method="post">	
				<input type="hidden" name="email" value="<%= session.getAttribute("email") %>" />
			
			    <label>Abilitá:</label>
				<select name="skillname" id="skillname">
						<%if ( resultlist != null && resultlist.size()>0 ) {
							for (int i=0; i<resultlist.size(); i++) {
						%>
							<option value=<%= resultlist.get(i) %>><%= resultlist.get(i) %></option>
						<%	}
						  }
						%>
				</select><br>
				<label>Descrizione:</label>
				<input type="text" name="descr" />
				<input type="submit" value="Submit" /><br>
				</form>
				</td>
			</tr>
			</table>
		</td>
	</tr>
</table>
</body>
</html>
