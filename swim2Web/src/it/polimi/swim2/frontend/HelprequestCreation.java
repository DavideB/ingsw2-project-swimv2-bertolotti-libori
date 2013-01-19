package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBSkill;

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
 * Servlet implementation class HelprequestCreation
 */
public class HelprequestCreation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	private StatelessEJBSkill statelessBeanSkill;
	private StatelessEJBHelprequest statelessBeanHelprequest;   

	@Override
	public String getServletInfo() {
		return "This servlet handles the requests to create new requests for help";
		}
	  
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	      statelessBeanSkill = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
	      statelessBeanHelprequest = (StatelessEJBHelprequest) context.lookup("swim2/HelprequestManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public HelprequestCreation() {
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
		init();
    	String email = request.getParameter("email");
    	String skillname = request.getParameter("skillname");
    	String descr = request.getParameter("descr");
    	byte isForFriend = Byte.parseByte("1");
    	int sen_id = statelessBean.getUser(email).getId();

    	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    	Date sentdate = Calendar.getInstance().getTime();
    	
    	int skillId = statelessBeanSkill.getSkill(skillname).getId();

	    if 	//registrazione Helprequest ok    
	    	//( statelessBeanHelprequest.createHelprequest(ans_id, ansdate, isForFriend, message, sen_id, sentdate, skillId) )
	    	( statelessBeanHelprequest.createHelprequest(isForFriend, descr, sen_id, sentdate, skillId) )
	    {
	    	request.getRequestDispatcher("services/nreg.jsp").forward(request, response);
	    }
	    
	    else
	    	///registrazione utente fallita 
	    	{
	    	request.getSession().setAttribute("error","Registration failed. Please verify data.\n");
	    	//response.sendRedirect("error.jsp");
	    	response.sendRedirect("services/nreg.jsp");
	    	}
		return;   	
	}

}
