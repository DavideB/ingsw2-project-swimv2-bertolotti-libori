<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- jsp:directive.page import="org.displaytag.*" /-->
<jsp:directive.page import="it.polimi.swim2.persistence.*" />
<jsp:directive.page import="java.util.List" />
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
		   					<p>Richieste d'aiuto dei tuoi amici</p>
		   					<display:table name="friendsRequests" decorator="it.polimi.swim2.decorators.HelpRequestDecorator">
			   					<display:column property="name" title="nome"/>
								<display:column property="askToRequestLink" title=""/>
							</display:table>
		   				</div>
		   				<div class="span6">
		   					<p>Altre richieste d'aiuto</p>
		   					<display:table name="otherRequests" decorator="it.polimi.swim2.decorators.HelpRequestDecorator">
			   					<display:column sortable="true" property="name" title="nome"/>
								<display:column property="askToRequestLink" title=""/>
							</display:table>
		   				</div>
		    		</div>
		    		<div class="row-fluid">
					<div class="span8">
						<form name="modUserData" action="SendNewHelpRequest" method="post">
							<div class="row">
								<div class="span4">Seleziona l'abilità di cui hai bisogno</div>
								<div class="span8">
									<select name="skill" id="skill">
										<%for (Skill curr : (List<Skill>)request.getAttribute("availableSkills") ) { %>
											<option value=<%= curr.getName() %>><%= curr.getName() %></option>
											<% } %>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="span4">Descrivi brevemente i tuoi bisogni</div>
								<div class="span6"><input name="message" type="text"></div>
								<div class="span2"><input type="submit"/></div>
							</div>
						</form>
					</div>
					<div class="span4">
					<!-- qui verrà visualizzata l'immagine del profilo -->
						<%@ include file="profileimage.jsp" %>
					</div>
				</div>
			</div>
			
			<div id="footer">
				<%@ include file="footer.jsp" %>
			</div>
		</div>
	</div>
</body>
</html>