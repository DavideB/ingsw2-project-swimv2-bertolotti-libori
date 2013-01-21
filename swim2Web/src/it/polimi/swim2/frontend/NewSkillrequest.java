package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBSkillrequest;
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
 * Servlet implementation class NewSkillrequest
 */
public class NewSkillrequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private StatelessEJB statelessBean;
	private StatelessEJBSkillrequest statelessBeanSkillreq;
	
	@Override
	public String getServletInfo() {
		return "This servlet handles the requests to register new skills";
		}
	  
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	      statelessBeanSkillreq = (StatelessEJBSkillrequest) context.lookup("swim2/SkillrequestManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewSkillrequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		//controlla che l'accesso sia stato effettuato
		if (username==null) {
			request.getSession().setAttribute("error","Devi prima effettuare l'accesso!");
			response.sendRedirect("access/home.jsp");
			return;
		}
		String message = request.getParameter("skillrequest");
    	String email = request.getParameter("email");
    	int mak_id = statelessBean.getUser(email).getId();

    	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    	//Date mak_date = Calendar.getInstance().getTime();
    	Date mak_date = new Date();
    	try {mak_date = sdf.parse("01/01/0001");} catch(Exception e) {e.printStackTrace();};
    	//Date mak_date=null;

	    if 	//registrazione richiesta di aggiunta skill ok   
	    	( statelessBeanSkillreq.createSkillrequest(mak_id, mak_date, message) )
	    {
	    	request.getRequestDispatcher("WEB-INF/registered/skillmgmt.jsp").forward(request, response);
	    }
	    
	    else
	    	///registrazione utente fallita 
	    	{
	    	request.getSession().setAttribute("error","New skill request registration failed. Please verify data.\n");
	    	//response.sendRedirect("error.jsp");
	    	response.sendRedirect("WEB-INF/registered/skillmgmt.jsp");
	    	}
		return;   	
	}

}
