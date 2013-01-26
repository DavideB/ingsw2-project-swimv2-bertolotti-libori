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
<script type='text/javascript'>
	displayRow = -1;
	function openPopUp(el, val) {
		var table = document.getElementById("allOtherUsers");
		if (displayRow != -1) {
			table.deleteRow(displayRow);
		}
		var target = document.getElementById("target");
		target.value = val;
		displayRow = el.parentNode.parentNode.rowIndex + 1;
		var row = table.insertRow(el.parentNode.parentNode.rowIndex + 1);
		row.innerHTML = "<label for=\"message\" value=\"inserisci qui il tuo messaggio\"><input type=\"text\" name=\"message\"/>"
				+ "<input type=\"submit\">Invia </input>";

	}
</script>
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
						<form id="messageform" type="post" action="SendFriendshipRequest">
							<display:table id="allOtherUsers" name="allOtherUsers"
								decorator="it.polimi.swim2.decorators.RegisteredUserDecorator">
								<display:column property="name" title="nome" />
								<display:column property="surname" title="cognome" />
								<display:column property="friendshipLink"
									title="richiesta amicizia" />
								<display:column property="helpReqLink" title="richiesta d'aiuto" />
							</display:table>
							<input type="text" id="target" />
						</form>
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