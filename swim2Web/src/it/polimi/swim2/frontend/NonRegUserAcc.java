package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
//import it.polimi.swim2.persistence.User;
//import it.polimi.swim2.persistence.Registered;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NonRegUserAcc
 */
public class NonRegUserAcc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	Pattern p;
	
	@Override
	public String getServletInfo() {
		return "This servlet handles the access requests from non registered users";
		}
	  
	  @Override
	public void init() throws ServletException {
	    try {
		  p = Pattern.compile(".+@.+\\.[a-z]+");		
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NonRegUserAcc() {
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
    	String email = request.getParameter("email");
    	//mi assicuro che l'indirizzo e-mail sia effettivamente tale
    	Matcher m = p.matcher(email);
    	if (!m.matches()) {
    		request.getSession().setAttribute("error", "L'indirizzo e-mail inserito non è valido!");
    		response.sendRedirect("error.jsp");
    		return;
    	}
    	String username = (String)request.getSession().getAttribute("username"); 
        String password = (String)request.getSession().getAttribute("password");
    	//PrintWriter writer = response.getWriter();
    	//response.sendRedirect(response.encodeRedirectURL("/listskills"));
    	if ( username!=null ) {
    		request.getSession().setAttribute("error", "You should first logout before trying to access as a non registered user");
    		response.sendRedirect( "access/home.jsp" );
    		//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/logout");
    		//dispatcher.include(request,response);
    		return;
    	}
    	if /* 
	    	* se e-mail non esiste, crea un nuovo utente con l'indirizzo e-mail specificato,
	        * se e-mail esiste verifica che non appartenga ad un utente registrato
	        */
	    	( statelessBean.verifyOrCreateUser( email) ) 
	    {
	    	request.getSession().setAttribute("email", email);
	    	request.getRequestDispatcher("services/nreg.jsp").forward(request, response);
	    }
	    
	    else
	    	//e-mail giá presente e associato a utente registrato
	    	{
	    	request.getSession().setAttribute("error","Please verify e-mail.\n" +
	    			"Registered users and Admin should login using their UserId and Password\n");
	    	//response.sendRedirect("error.jsp");
	    	response.sendRedirect("access/home.jsp");
	    	}
		return;   	
	}

}
