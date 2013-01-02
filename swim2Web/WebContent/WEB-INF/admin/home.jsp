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
	Benvenuto <%= session.getAttribute("username") %>
	
	<display:table name="test" />
</body>
</html>