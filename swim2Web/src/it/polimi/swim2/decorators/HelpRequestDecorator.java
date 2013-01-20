package it.polimi.swim2.decorators;

import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Registered;

import org.displaytag.decorator.TableDecorator;

public class HelpRequestDecorator extends TableDecorator {
	public String getAskToRequestLink() {
		Helprequest hr = (Helprequest)getCurrentRowObject();
		String link = "<a href=\"AnswerToHelpRequest?target="+hr.getId()+"\">"+"Rispondi"+ "</a>";
		return link;
	}
}
