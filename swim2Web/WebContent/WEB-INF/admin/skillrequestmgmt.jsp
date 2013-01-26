<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- jsp:directive.page import="org.displaytag.*" /-->
<jsp:directive.page import="it.polimi.swim2.persistence.*" />
<jsp:directive.page import="java.util.ArrayList" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" language="JavaScript">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class=row-fluid>
		<div class="span8 offset2">
			<div id="header">
				<%@ include file="header.jsp" %>
			</div>
			
			<div id="main">
				    <div class="row">
		   				<div class="span6">
		   					<p>Abilità Disponibili</p>
		   					<display:table name="availableSkills" decorator="it.polimi.swim2.decorators.SkillDecorator">
			   					<display:column property="name" title="nome"/>
								
							</display:table>
		   				</div>
		   				<div class="span6">
		   					<p>Nuove Abilità Richieste</p>
		   					<display:table name="users" decorator="it.polimi.swim2.decorators.SkillDecorator">
			   					<display:column sortable="false" property="email" title="richiedente"/>
							</display:table>
							<display:table name="newabilityrequests" decorator="it.polimi.swim2.decorators.SkillDecorator">
			   					<display:column sortable="false" property="message" title="skill"/>
			   					<display:column property="AcceptLink" title="Accept"/>
								<display:column property="RejectLink" title="Reject"/>
								<div class="row-fluid">
								<div class="span8">
								<form name="modUserData" action="NewSkillrequest" method="post">
									<div class="row">
										<div class="span4">Messaggio di risposta al richiedente </div>
										<div><input type="hidden" name="email" value=<%=(String)session.getAttribute("email")%>></div>
										<div class="span8"><input name="skillrequest" type="text"></div>
									</div>
							</form>
							</display:table>
		   				</div>
		    		</div>

			<div id="footer">
				<%@ include file="footer.jsp" %>
			</div>
		</div>
	</div>
</body>
</html>

