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
		   				<div class="span8">
		   					<display:table name="availableSkills">
		   					</display:table>
		   				</div>
		    		</div>
		    		<div class="row-fluid">
					<div class="span8">
						<form name="modUserData" action="SendNewAbilityRequest" method="post" onsubmit="return checkPwd(this);">
							<div class="row">
								<div class="span4">Inserisci il nome della nuova abilità e una breve descrizione dei motivi che ti spingono a richiederla. La richiesta sarà vagliata dall'ammistratore di sistema appena possibile.</div>
								<div class="span8"><input name="message" type="text"></div>
							</div>
						</form>
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