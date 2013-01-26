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
								<display:column property="addAbilityLink" title=""/>
							</display:table>
		   				</div>
		   				<div class="span6">
		   					<p>Tue Abilità</p>
		   					<display:table name="yourSkills" decorator="it.polimi.swim2.decorators.SkillDecorator">
			   					<display:column sortable="true" property="name" title="nome"/>
								<display:column property="removeAbilityLink" title=""/>
							</display:table>
		   				</div>
		    		</div>
		    		<div class="row-fluid">
					<div class="span8">
						<form name="modUserData" action="NewSkillrequest" method="post">
							<div class="row">
								<div class="span4">Inserisci il nome della nuova abilità </div> <!-- e una breve descrizione dei motivi che ti spingono a richiederla. La richiesta sarà vagliata dall'ammistratore di sistema appena possibile.</div> -->
											<input type="hidden" name="email" value=<%=(String)session.getAttribute("email")%>></div>
								<div class="span8"><input name="skillrequest" type="text"></div>
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