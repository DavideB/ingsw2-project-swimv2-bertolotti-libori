package it.polimi.swim2.decorators;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Satisfiedhelprequest;

import org.displaytag.decorator.TableDecorator;

public class SatisfiedHelpRequestDecorator extends TableDecorator {
	private StatelessEJBHelprequest statelessBean;
	public SatisfiedHelpRequestDecorator() {
	     try {
				statelessBean = (StatelessEJBHelprequest) new InitialContext().lookup("swim2/HelpRequestManager/remote");
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	

}
