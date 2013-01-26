package it.polimi.swim2.decorators;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Friendshiprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import org.displaytag.decorator.TableDecorator;

public class FriendshipDecorator extends TableDecorator  {
	private StatelessEJB statelessBean;
	public FriendshipDecorator() {
	      try {
			statelessBean = (StatelessEJB) new InitialContext().lookup("swim2/UserManager/remote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public String getRemoveLink() {
		Registered r = (Registered)getCurrentRowObject();
		String link = "<a href=\"RemoveFriend?target="+r.getId()+"\">"+"Aggiungi questa abilità al tuo curriculum"+ "</a>";
		return link;
	}
	
	public String getAcceptLink() {
		Friendshiprequest f = (Friendshiprequest)getCurrentRowObject();
		String link = "<a href=\"#\">"+"accetta"+ "</a>";
		return link;
	}
	
	public String getRejectLink() {
		Friendshiprequest f = (Friendshiprequest)getCurrentRowObject();
		String link = "<a href=\"#\">"+"rifiuta"+ "</a>";
		return link;
	}
	

	public String getSender() {
		Friendshiprequest f = (Friendshiprequest)getCurrentRowObject();
		String email = statelessBean.getUser(f.getSent_id()).getEmail();
		return email;
	}

}