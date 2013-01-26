package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.interfaces.StatelessEJBSatisfiedhelprequest;
import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import java.io.IOException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageHelpRequest
 */
public class ManageHelpRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private StatelessEJBHelprequest helprequests;
    StatelessEJBSkill skills;
	private StatelessEJBSatisfiedhelprequest statelessBeanSatisfiedhelprequest;  

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageHelpRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try {
	    	
  	      Context context = new InitialContext();
  	      helprequests = (StatelessEJBHelprequest) context.lookup("swim2/HelprequestManager/remote");
  	      skills = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
    	  statelessBeanSatisfiedhelprequest = (StatelessEJBSatisfiedhelprequest) context.lookup("swim2/SatisfiedhelprequestManager/remote");

  	    } catch (NamingException e) {
  	      e.printStackTrace();
  	    }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Registered user = (Registered) request.getSession().getAttribute("userData");
		//controlla che l'accesso sia stato effettuato
		if (user==null) {
			request.getSession().setAttribute("error","Devi prima effettuare l'accesso!");
			response.sendRedirect("access/home.jsp");
			return;
		}
		List<Skill> availableSkills = skills.getAllRegSkills();
		request.setAttribute("availableSkills",  availableSkills);
		request.setAttribute("friendsRequests", helprequests.getYourFriendsHelprequests(user));
		request.setAttribute("othersRequests", helprequests.getOthersHelprequests(user));	
		request.setAttribute("satisfiedrequests", helprequests.getSatisfied(user));		
    	request.getRequestDispatcher("WEB-INF/registered/Gestione Richieste d'aiuto.jsp").forward(request, response);
    	return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
