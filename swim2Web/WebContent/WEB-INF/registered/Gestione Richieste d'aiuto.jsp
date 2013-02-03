<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- jsp:directive.page import="org.displaytag.*" /-->
<jsp:directive.page import="it.polimi.swim2.persistence.*" />
<jsp:directive.page import="java.util.List" />
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<html lang="it">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" language="JavaScript">
	<script src="http://code.jquery.com/jquery-latest.js">
</script>
<script src="js/bootstrap.min.js"></script>
<script type='text/javascript'>
	displayRow = -1;
	function openPopUp(el, val, id) {
		var table = document.getElementById(id);
		if (displayRow != -1) {
			table.deleteRow(displayRow);
		}
		//var target = document.getElementById("target");
		//target.value = val;
		displayRow = el.parentNode.parentNode.rowIndex + 1;
		var row = table.insertRow(el.parentNode.parentNode.rowIndex + 1);
		row.innerHTML = "<label for=\"message\" value=\"inserisci qui il tuo messaggio\"/><input type=\"text\" name=\"message\"/>"
				+ "<input type=\"text\" id=\"reqId\" name=\"reqId\" value=\""+val+"\"/>"
				+ "<input type=\"submit\" value=\"invia\"/>";
	}
	
	displayFeedback = -1;
	function openPopUpFeed(el, val) {
		var table = document.getElementById("satisfiedrequests");
		if (displayFeedback != -1) {
			table.deleteRow(displayRow);
		}
		//var target = document.getElementById("target");
		//target.value = val;
		displayRow = el.parentNode.parentNode.rowIndex + 1;
		var row = table.insertRow(el.parentNode.parentNode.rowIndex + 1);
		row.innerHTML = "<label for=\"feedback\" value=\"Valuta l'aiuto ricevuto:\"/>"
				+ "<select name=\"feedback\" id=\"feedback\"/>"
				+ "<option value=\"1\"/>1</option>"
				+ "<option value=\"2\"/>2</option>"
				+ "<option value=\"3\"/>3</option>"
				+ "<option value=\"4\"/>4</option>"
				+ "<option value=\"5\"/>5</option>"
				+"</select>"
				+ "<input type=\"hidden\" id=\"target\" name=\"reqId\" value=\""+val+"\"/>"
				+ "<input type=\"submit\" value=\"invia\"/>";
	}
</script>
</head>
<body>
	<div class=row-fluid>
		<div class="span12" id="header">
			<%@ include file="header.jsp"%>
		</div>
		<div class="span8 offset2">
			<div class="span6" >
				Benvenuto <%= session.getAttribute("username") %>
			</div>
			<div class="span6" >
				<jsp:include page="profileimage.jsp"/> 
			</div>
		</div>
		<div class="span8 offset2">



			<div id="main">
				<div class="row">
					<div class="span4">
						<p>Richieste d'aiuto dei tuoi amici</p>
						<form action="AnswerToHelpRequest" method="post">
							<display:table id="friendsRequests" name="friendsRequests"
								decorator="it.polimi.swim2.decorators.HelpRequestDecorator">
								<display:column property="message" title="messaggio" />
								<display:column property="askToFriendLink" title="" />
							</display:table>
					</div>
					<div class="span4">
						<p>Altre richieste d'aiuto</p>
						<display:table id="othersRequests" name="otherRequests"
							decorator="it.polimi.swim2.decorators.HelpRequestDecorator">
							<display:column property="message" title="messaggio" />
							<display:column property="askToOtherLink" title="" />
						</display:table>
					</div>
					<div class="span4">
						<p>Richieste d'aiuto di utenti non registrati</p>
						<display:table id="nonRegRequests" name="nonRegRequests"
							decorator="it.polimi.swim2.decorators.NonRegRequestDecorator">
							<display:column property="message" title="messaggio" />
							<display:column property="responseLink" title="" />
						</display:table>
					</div>
					</form>
				</div>
				<div class="row">
					<form name="modUserData" action="SendFeedback" method="post">
						<div class="span12">
							<p>Hai ricevuto le seguenti risposte:</p>
							<display:table id="satisfiedrequests" name="satisfiedrequests"
								decorator="it.polimi.swim2.decorators.HelpRequestDecorator">
								<display:column property="message" title="nome" />
								<display:column property="sendFeedbackLink" title="" />
							</display:table>
						</div>
					</form>
				</div>
				<div class="row-fluid">
					<div class="span8">
						<form name="modUserData" action="SendHelpRequest" method="post">
							<div class="row">
								<div class="span4">Seleziona l'abilità di cui hai bisogno</div>
								<div class="span8">
									<select name="skillname" id="skillname">
										<%
											for (Skill curr : (List<Skill>) request
													.getAttribute("availableSkills")) {
										%>
										<option value=<%=curr.getName()%>><%=curr.getName()%></option>
										<%
											}
										%>
									</select>
									<div>
										<input type="checkbox" name="isForFriend" /> Vuoi chiedere
										aiuto solo ai tuoi amici?</input>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="span4">Descrivi brevemente i tuoi bisogni</div>
								<div class="span6">
									<input name="message" type="text" />
								</div>
								<div class="span2">
									<input type="submit" />
								</div>
							</div>
						</form>
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