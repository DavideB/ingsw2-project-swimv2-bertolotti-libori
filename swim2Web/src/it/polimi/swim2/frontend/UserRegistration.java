package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
//import it.polimi.swim2.persistence.User;
//import it.polimi.swim2.persistence.Registered;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class UserRegistration
 */
public class UserRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	
	@Override
	public String getServletInfo() {
		return "This servlet handles the access requests from non registered users";
		}
	  
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
		String day = request.getParameter("day");
    	String month = request.getParameter("month");
    	String year = request.getParameter("year");
    	SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
    	Date birthdate = new Date();
        try {
        	birthdate = sdf.parse(day + "/" + month + "/" + year);
    	} catch (ParseException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    
	    if 	//registrazione utente ok    
	    	( statelessBean.registerUser(firstname, lastname, email, password, birthdate) ) 
	    {
	    	request.getSession().setAttribute("email", email);
	    	request.getSession().setAttribute("password", password);
	    	request.getSession().setAttribute("firstname", firstname);
	    	request.getSession().setAttribute("lastname", lastname);
	    	request.getSession().setAttribute("birthdate", birthdate);
	    	request.getRequestDispatcher("access/login.jsp").forward(request, response);
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
