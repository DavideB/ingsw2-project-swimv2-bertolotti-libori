package it.polimi.swim2.frontend;


import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.interfaces.StatelessEJBSatisfiedhelprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnswerToHelpRequest
 */
public class AnswerToHelpRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	private StatelessEJBHelprequest helprequests;
	private StatelessEJBSatisfiedhelprequest statelessBeanSatisfiedhelprequest;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerToHelpRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	try {
	    	
  	     Context context = new InitialContext();
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
		
		String ans = request.getParameter("message");
		int reqId = Integer.parseInt(request.getParameter("reqId"));
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    	Date date = Calendar.getInstance().getTime();
    	helprequests.setAnsDate(reqId);
	    if 	//registrazione Satisfiedhelprequest ok    
	    	( statelessBeanSatisfiedhelprequest.createSatisfiedhelprequest(reqId, date, 0) )
		    {
		    	response.sendRedirect("ManageHelpRequest");
		    	return;
		    }
	    
	    else//registrazione Satisfiedhelprequest fallita 
	    	{
	    		request.getSession().setAttribute("error","Creation Satisfiedhelprequest failed. Please verify data.\n");
		
	    		request.getRequestDispatcher("WEB-INF/registered/Gestione Richieste d'aiuto.jsp").forward(request, response);
	    		return;
	    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);	
	}
	
	

}