package it.polimi.swim2.frontend;

import it.polimi.swim2.business.RegisteredSkillManager;
import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBRegisteredSkill;
import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Registered;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddSkill
 */
public class RemoveSkill extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
    private StatelessEJBSkill skills;     
    private StatelessEJBRegisteredSkill sklMng;
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
    public RemoveSkill() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try {
	    	
  	      Context context = new InitialContext();
  	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
  	      skills = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
  	      sklMng = (StatelessEJBRegisteredSkill) context.lookup("swim2/RegisteredSkillManager/remote");
  	    } catch (NamingException e) {
  	      e.printStackTrace();
  	    }
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Registered r = (Registered) request.getSession().getAttribute("userData");
		//controlla che l'accesso sia stato effettuato
		if (r==null) {
			request.getSession().setAttribute("error","Devi prima effettuare l'accesso!");
			response.sendRedirect("access/home.jsp");
			return;
		}
		int skill_id = Integer.parseInt(request.getParameter("target"));
		sklMng.unLinkSkill(r, skills.getSkill(skill_id) );
		response.sendRedirect("ManageSkills");
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
