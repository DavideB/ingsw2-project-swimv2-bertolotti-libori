package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Registered;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Servlet implementation class Logout
 */

public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private StatelessEJB statelessBean;   
    /**
     * @see HttpServlet#HttpServlet()
     */

    public Logout() {
        super();
        // TODO Auto-generated constructor stub
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
		init();
		
		String username = (String) request.getSession().getAttribute("username");
		Registered r = (Registered) request.getSession().getAttribute("data");
		PrintWriter writer = response.getWriter();
	    writer.write("User: " + username + "\n");

		if (username != null) {//se c'é un utente registrato annulla i suoi riferimenti nella sessione
			request.getSession().setAttribute("username", null);
			request.getSession().setAttribute("password", null);
			request.getSession().setAttribute("data", null);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		else {//se non c'è nessuno utente loggato scrive nel parametro "error" e esegue una redirect verso la pagina di origine
			request.getSession().setAttribute("error","Logout anomalo, utente con email " + username + " non é logged-in");
			response.sendRedirect("access/home.jsp");
			return;
		}		
	}

}

