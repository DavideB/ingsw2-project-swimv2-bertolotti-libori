package it.polimi.swim2.decorators;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Satisfiedhelprequest;

import org.displaytag.decorator.TableDecorator;

public class NonRegRequestDecorator extends TableDecorator {
	StatelessEJB statelessBean;
	StatelessEJBHelprequest ehr;

	public NonRegRequestDecorator() {
		try {
			statelessBean = (StatelessEJB) new InitialContext()
					.lookup("swim2/UserManager/remote");
			ehr = (StatelessEJBHelprequest) new InitialContext()
					.lookup("swim2/HelprequestManager/remote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getResponseLink() {
		Helprequest hr = (Helprequest) getCurrentRowObject();
		String email = statelessBean.getUser(hr.getSen_id()).getEmail();
		String link = "<a href=\"mailto:" + email + "\">" + "Invia e-mail"
				+ "</a>";
		return link;
	}

	public String getDeleteLink() {
		Helprequest hr = (Helprequest) getCurrentRowObject();
		String link = "<a href=\"NonRegReqDelete?"+hr.getId()+"\">" + "Elimina richiesta inviata" + "</a>";
		return link;
	}

}