package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBSkillrequest;

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
 * Servlet implementation class SkillRejection
 */
public class SkillRejection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	private StatelessEJBSkill statelessBeanSkill;
	private StatelessEJBSkillrequest statelessBeanSkillrequest;
	
	@Override
	public String getServletInfo() {
		return "This servlet handles the rejection of new skill requests";
		}
	  
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	      statelessBeanSkill = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
	      statelessBeanSkillrequest = (StatelessEJBSkillrequest) context.lookup("swim2/SkillrequestManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SkillRejection() {
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
    	int mak_id = statelessBean.getUser(email).getId();
    	int req_id = Integer.parseInt(request.getParameter("req_id"));

    	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    	Date defdate = Calendar.getInstance().getTime();

	    if 	//cancellazione richiesta riuscita   
	    	//( statelessBeanSkill.createSkill(skillname) )
	    	( statelessBeanSkillrequest.deleteSkillrequest( req_id)>0 )
	    	{
	    	//request.getRequestDispatcher("WEB-INF/admin/skillrequestmgmt.jsp").forward(request, response);
	    	//request.getRequestDispatcher("services/skillrequestmgmt.jsp").forward(request, response);
	    	response.sendRedirect("ManageNewSkillrequests");
	    	return;
	    	}
	    
	    else
	    	///registrazione utente fallita 
	    	{
	    	request.getSession().setAttribute("error","Cancellation of request failed. Please verify data.\n");
	    	//response.sendRedirect("error.jsp");
	    	//request.getRequestDispatcher("WEB-INF/admin/skillrequestmgmt.jsp").forward(request, response);
	    	response.sendRedirect("ManageNewSkillrequests");
	    	}
		return;   	
	}

}
