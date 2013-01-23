package it.polimi.swim2.decorators;

import it.polimi.swim2.persistence.Registered;

import org.displaytag.decorator.TableDecorator;

public class RegisteredUserDecorator extends TableDecorator {
	
	public String getFriendshipLink() {
		Registered r = (Registered)getCurrentRowObject();
		String link = "<a href=\"#\" onclick=\"openPopUp(this, "+ r.getId()+")\">"+"Invia Amicizia"+ "</a>";
		return link;
	}
	
	public String getHelpReqLink() {
		Registered r = (Registered)getCurrentRowObject();
		String link = "<a href=\"SendHelpRequest?target="+r.getId()+"\">"+"Chiedi Aiuto"+ "</a>";
		return link;
	}
}
