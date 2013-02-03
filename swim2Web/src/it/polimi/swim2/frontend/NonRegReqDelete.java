package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.interfaces.StatelessEJBSatisfiedhelprequest;
import it.polimi.swim2.interfaces.StatelessEJBSkill;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NonRegReqDelete
 */
public class NonRegReqDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StatelessEJBHelprequest helprequests;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NonRegReqDelete() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
	    	
  	      Context context = new InitialContext();
  	      helprequests = (StatelessEJBHelprequest) context.lookup("swim2/HelprequestManager/remote");

  	    } catch (NamingException e) {
  	      e.printStackTrace();
  	    }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		helprequests.delete(id);
		response.sendRedirect("ManageHelprequest");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
