package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessFriendshipBean;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageFriendship
 */
public class ManageFriendship extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StatelessEJB statelessBean;
	private StatelessFriendshipBean friendship;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageFriendship() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init();
		try {
	    	
	  	      Context context = new InitialContext();
	  	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	  	      friendship = (StatelessFriendshipBean) context.lookup("swim2/FriendshipManager/remote");
	  	    } catch (NamingException e) {
	  	      e.printStackTrace();
	  	    }
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = (String) request.getSession().getAttribute("username");
		//controlla che l'accesso sia stato effettuato
		if (username==null) {
			request.getSession().setAttribute("error","Devi prima effettuare l'accesso!");
			response.sendRedirect("access/home.jsp");
			return;
		}
		request.getSession().setAttribute("friends", friendship.getAllFriends());
		request.getRequestDispatcher("WEB-INF/registered/Gestione Amicizia.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
