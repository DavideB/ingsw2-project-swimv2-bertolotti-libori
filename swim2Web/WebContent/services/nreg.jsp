<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Servizi per Utenti Non Registrati</title>
</head>
<body>
<div><a href="/swim2Web/index.jsp">Home</a></div>
<h1>Servizi per Utenti Non Registrati</h1>
	<%= session.getAttribute("error")  %>
	<%  session.setAttribute("error", null); %>
	Benvenuto <%= session.getAttribute("email") %>
<h2>Servizio di Registrazione</h2>	
<p>

Per registrarti, riempi i campi con i tuoi dati e clicca su "Register":

</p>	
	<form action="/swim2Web/userregistration" method="post">
		<input type="hidden" name="email" value="<%= session.getAttribute("email") %>" />
		Password: <input type="text" name="password" /><br />
		First Name: <input type="text" name="firstname" /><br />
		Surname: <input type="text" name="lastname" /><br />
		Birthdate: 
		<table border=>
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
			</tr>
		</table>
		<input type="submit" value="Register" />
		</form>
</body>
</html>