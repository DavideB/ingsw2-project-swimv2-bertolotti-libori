
<% 
	String imgUrl = ((it.polimi.swim2.persistence.Registered)session.getAttribute("userData")).getImageUrl();
	if (imgUrl!=null) 
%>
<img alt="Immagine del Profilo" src="http://<%=imgUrl%>">
<%; %>