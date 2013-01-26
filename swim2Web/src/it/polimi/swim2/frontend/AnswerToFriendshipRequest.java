package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessFriendshipBean;
import it.polimi.swim2.persistence.Registered;

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
 * Servlet implementation class AnswerFriendshipRequest
 */
public class AnswerToFriendshipRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	private StatelessEJB statelessBean;
	private StatelessFriendshipBean friendship;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerToFriendshipRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		Registered r = (Registered) request.getSession().getAttribute("userData");
		//controlla che l'accesso sia stato effettuato
		if (r==null) {
			request.getSession().setAttribute("error","Devi prima effettuare l'accesso!");
			response.sendRedirect("access/home.jsp");
			return;
		}
		int target = Integer.parseInt(request.getParameter("target"));
		String action = request.getParameter("action");
		if (action.equals("reject")) friendship.rejectFriendship(target);
		else if (action.equals("accept")) friendship.acceptFriendship(target);
		response.sendRedirect("ManageFriendship");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
