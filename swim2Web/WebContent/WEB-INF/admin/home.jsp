<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:directive.page contentType="text/html; charset=UTF-8" />
<jsp:directive.page import="it.polimi.swim2.persistence.*" />
<!-- jsp:directive.page import="org.displaytag.*" /-->
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<div><a href="/swim2Web/index.jsp">Home</a></div>
	<div><a href="/swim2Web/logout">Logout</a></div>
	Benvenuto <%= session.getAttribute("username") %>
	
	<display:table name="test" />
	<h1>Welcome to the SWIMv2 Application</h1>
		<div><a href="/swim2Web/services/skillmgmt.jsp">Gestione Abilitá</a></div>
		<div><a href="/swim2Web/services/skillrequestmgmt.jsp">Gestione Richieste Aggiunta Abilitá</a></div>
</body>
</html>