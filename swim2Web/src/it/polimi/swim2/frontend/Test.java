package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
 * Servlet implementation class Test
 */

public class Test extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	  
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJB) context.lookup("swim2/UserManager/remote");
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }
	  }
	  
	  @Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
	                     throws ServletException, IOException {
	    doGet(req, resp);
	  }
	  
	  @Override
	public void doGet(HttpServletRequest request, HttpServletResponse resp)
	                    throws ServletException, IOException {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String password = request.getParameter("password");
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	    try {
			statelessBean.createUser(name, surname, email, password, sdf.parse("20/07/1990"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    displayPeople(resp);
	  }
	  
	  private void displayPeople(HttpServletResponse resp) throws IOException {
	    PrintWriter writer = resp.getWriter();
	    writer.write("List of Person ejbs:\n");
	    List<User> people = statelessBean.getAllUsers();
	    for (User person : people) {
	      writer.write("Person retrieved: " + person.getEmail() + "\n");
	    }
	    
	       
	  }
}
