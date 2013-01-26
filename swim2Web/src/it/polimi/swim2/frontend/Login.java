package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Registered;

import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	private StatelessEJB statelessBean;
    private StatelessEJBSkill skills;   
 
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
  	      skills = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		throw new ServletException("Il metodo get non è supportato. Compila il form di login per ottenere l'accesso.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		init();
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		request.getSession().setAttribute("isadmin", false);
		//controlla se è un utente registrato
		Registered r;
		if ((r = statelessBean.loginReg(username, password))!=null) {
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("email", username);
			request.getSession().setAttribute("userData", r);			
			response.sendRedirect("ManageUserData");
//request.getRequestDispatcher("services/reg.jsp").forward(request, response);
			return;
		}
		//controlla se è un admin
		Admin a;
		if ((a = statelessBean.loginAdmin(username, password))!=null) {
			request.getSession().setAttribute("username", username);
			request.getSession().setAttribute("email", username);
			request.getSession().setAttribute("data", r);
			request.getSession().setAttribute("isadmin", true);
			request.getRequestDispatcher("WEB-INF/admin/home.jsp").forward(request, response);
			return;
		}
		//se non è nessuno dei due scrive l'errore nel parametro "error" e esegue una redirect verso la pagina d'origine
		request.getSession().setAttribute("error","Indirizzo email " + username + " o password "+password+ " non corretti");
		response.sendRedirect("error.jsp");
		return;
	}

}
