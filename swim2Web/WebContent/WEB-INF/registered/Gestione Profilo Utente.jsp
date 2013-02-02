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
<script type="text/javascript" language="JavaScript">
<!--

	function checkPwd(theForm) {
		if (theForm.oldPwd.value != theForm.ConfirmOldPwd.value) {
			alert('Le vecchie password non corrispondono!');
			return false;
		}
		if (theForm.newPwd.value != theForm.ConfirmNewPwd.value) {
			alert('Le nuove password non corrispondono!');
			return false;
		}
		if (theForm.oldPwd.value != theForm.newPwd.value) {
			alert('La vecchia password e la nuova non devono essere uguali!');
			return false;
		}
		if (theForm.newPwd.lenght < 6) {
			alert('La lunghezza della password deve essere maggiore di 5 caratteri!');
			return false;
		}
		return true;

	}
//-->
</script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
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
					<div class="span12">
						<display:table name="sessionScope.userData">
							<display:column property="name" title="nome"/>
							<display:column property="surname" title="cognome"/>
							<display:column property="birthdate" title="data di nascita"/>
						</display:table>
					</div>
				</div>

				<div class="row">
					<div class="span12">
						

						<form enctype="multipart/form-data" action="UploadImage"
							method="post">
							Scegli un'immagine per il profilo <input name="newImage"
								type="file">
							<div class="span12">
								<input type="submit" value="Conferma">
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