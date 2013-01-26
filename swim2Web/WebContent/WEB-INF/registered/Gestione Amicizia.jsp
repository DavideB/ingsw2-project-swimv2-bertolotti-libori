<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- jsp:directive.page import="org.displaytag.*" /-->
<jsp:directive.page import="it.polimi.swim2.persistence.*" />
<jsp:directive.page import="java.util.ArrayList" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<div class=row-fluid>
		<div class="span8 offset2">
			<div id="header">
				<%@ include file="header.jsp"%>
			</div>

			<div id="main">
				<div class="row">
					<div class="span8">
						<p>Di seguito sono visualizzate le richieste d'amicizia nei tuoi confronti </p>
						<display:table name="requests" decorator="it.polimi.swim2.decorators.FriendshipDecorator">
								<display:column property="sender" title="autore" />
								<display:column property="message" title="messaggio" />
								<display:column property="acceptLink" title=""/>
								<display:column property="rejectLink" title=""/>
									
						</display:table>
						Di seguito sono visualizzati i tuoi amici
						<display:table name="friends" decorator="it.polimi.swim2.decorators.FriendshipDecorator">
								<display:column property="name" title="nome" />
								<display:column property="surname" title="cognome" />
								<display:column property="removeLink"
									title="elimina" />
						</display:table>
					</div>
				</div>

			</div>
			<div class="span4">
				<!-- qui verrà visualizzata l'immagine del profilo -->
				<%@ include file="profileimage.jsp"%>
			</div>
		</div>
	</div>

	<div id="footer">
		<%@ include file="footer.jsp"%>
	</div>
	</div>
	</div>
</body>
</html>