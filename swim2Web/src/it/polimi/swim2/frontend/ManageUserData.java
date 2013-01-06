package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Registered;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageUserData
 */
public class ManageUserData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StatelessEJB statelessBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageUserData() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
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
		String userName=(String) request.getSession().getAttribute("username");
		Registered  r = statelessBean.getUserData(userName);
		List<Registered> l = new ArrayList<Registered>();
		l.add(r);
		request.setAttribute("userData", l);
		request.getRequestDispatcher("WEB-INF/registered/Gestione Profilo Utente.jsp").forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
