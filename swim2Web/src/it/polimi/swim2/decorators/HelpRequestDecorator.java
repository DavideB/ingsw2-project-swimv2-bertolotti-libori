package it.polimi.swim2.decorators;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Satisfiedhelprequest;

import org.displaytag.decorator.TableDecorator;

public class HelpRequestDecorator extends TableDecorator {
	public HelpRequestDecorator() {
	    
	}
	
	public String getAskToFriendLink() {
		Helprequest hr = (Helprequest)getCurrentRowObject();
		String link = "<a href=\"#\" onclick=\"openPopUp(this, "+ hr.getId()+", 'friendsRequests' );\"\">"+"Rispondi"+ "</a>";
		return link;
	}
	public String getAskToOtherLink() {
		Helprequest hr = (Helprequest)getCurrentRowObject();
		String link = "<a href=\"#\" onclick=\"openPopUp(this, "+ hr.getId()+", 'otherRequests' );\">"+"Rispondi"+ "</a>";
		return link;
	}
	public String getSendFeedbackLink() {
		Helprequest hr = (Helprequest)getCurrentRowObject();
		String link = "<a href=\"#\" onclick=\"openPopUpFeed(this, "+ hr.getId()+");\"\">"+"Invia Feedback"+ "</a>";
		return link;
	}

}
