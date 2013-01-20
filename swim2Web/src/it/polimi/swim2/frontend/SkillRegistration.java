package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.interfaces.StatelessEJB;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SkillRegistration
 */
public class SkillRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	private StatelessEJBSkill statelessBeanSkill;
	
	@Override
	public String getServletInfo() {
		return "This servlet handles the requests to register new skills";
		}
	  
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	      statelessBeanSkill = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkillRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String skillname = request.getParameter("skillname");
    	String email = request.getParameter("email");
    	String adminansw = request.getParameter("adminansw");
    	int lead_id = statelessBean.getUser(email).getId();

    	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    	Date defdate = Calendar.getInstance().getTime();

	    if 	//registrazione skill ok    
	    	//( statelessBeanSkill.createSkill(skillname) )
	    	( statelessBeanSkill.createSkill(skillname, defdate, adminansw, lead_id))
	    {
	    	//request.getRequestDispatcher("WEB-INF/admin/skillmgmt.jsp").forward(request, response);
	    	request.getRequestDispatcher("services/skillmgmt.jsp").forward(request, response);
	    }
	    
	    else
	    	///registrazione utente fallita 
	    	{
	    	request.getSession().setAttribute("error","Registration failed. Please verify data.\n");
	    	//response.sendRedirect("error.jsp");
	    	response.sendRedirect("services/skillmgmt.jsp");
	    	}
		return;   	
	}

}
