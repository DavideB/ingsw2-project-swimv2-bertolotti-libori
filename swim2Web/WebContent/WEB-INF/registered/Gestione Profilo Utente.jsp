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
<!--
//--------------------------------
// This code compares two fields in a form and submit it
// if they're the same, or not if they're different.
//--------------------------------
function checkPwd(theForm) {
    if (theForm.oldPwd.value != theForm.ConfirmOldPwd.value)
    {
        alert('Le vecchie password non corrispondono!');
        return false;
    } 
    if (theForm.newPwd.value != theForm.ConfirmNewPwd.value)
    {
        alert('Le nuove password non corrispondono!');
        return false;
    } 
    if (theForm.oldPwd.value != theForm.newPwd.value)
    {
        alert('La vecchia password e la nuova non devono essere uguali!');
        return false;
    }     
    if (theForm.newPwd.lenght < 6)
    {
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
		<div class="span8 offset2">
			<div id="header">
				<%@ include file="header.jsp" %>
			</div>
			
			<div id="main">
				    <div class="row">
		   				<div class="span12">
		   					<display:table name="userData" >
		   						<display:column property="name" />
		   						<display:column property="surname" />
		   						<display:column property="birthdate"/>
		   					</display:table>
		   				</div>
		    		</div>
				
				<div class="row-fluid">
					<div class="span8">
						<form name="modUserData" action="ModifyUserData" method="post" onsubmit="return checkPwd(this);">
							<div class="row">
								<div class="span4">Vecchia Password</div>
								<div class="span8"><input name="oldPwd" type="password"></div>
							</div>
							<div class="row">
								<div class="span4">Conferma vecchia Password</div>
								<div class="span8"><input name="confirmOldPwd" type="password"></div>
							</div>
							<div class="row">
								<div class="span4">Nuova Password</div>
								<div class="span8"><input name="newPwd" type="password"></div>
							</div>
							<div class="row">
								<div class="span4">Conferma nuova Password</div>
								<div class="span8"><input name="confirmNewPwd" type="password"></div>
							</div>
							<div class="span12"><input type="submit" value="Conferma"></div>	
						</form>
						
						<form action="UploadImage" method="post">
							Scegli un'immagine per il profilo<input name="newImage" type="file">
							<input type="submit" value="Conferma">
						
							<div class="span12"><input type="submit" value="Conferma"></div>	
						</form>
						
					</div>
					<div class="span4">
					<!-- qui verrà visualizzata l'immagine del profilo -->
						<% 
							String imgUrl = ((ArrayList<Registered>)session.getAttribute("userData")).get(0).getImageUrl();
							if (imgUrl!=null) 
						%>
							<img alt="Immagine del Profilo" src="img/users/<%=imgUrl%>">
						<%; %>
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