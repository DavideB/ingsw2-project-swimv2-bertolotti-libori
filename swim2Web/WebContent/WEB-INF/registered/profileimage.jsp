
<%
	String imgUrl = ((it.polimi.swim2.persistence.Registered) session
			.getAttribute("userData")).getImageUrl();
	if (imgUrl != null)
%>
<div style="float: right">
	<img alt="Immagine del Profilo" src="http://<%=imgUrl%>">
</div>
<%
	;
%>