package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.User;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.other.RegisteredJoinUser;

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
 * Servlet implementation class ListUsers
 */

public class ListUsers extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	 
	private String out = new String();
		
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
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	                     throws ServletException, IOException {
	    doGet(request, response);
	  }
	  
	  @Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	                    throws ServletException, IOException {
	    //displayPeople(response);
		  request.getSession().setAttribute("out", null);
		  listPeople(response);
		  request.getSession().setAttribute("out", out);
		  request.getRequestDispatcher("services/list.jsp").forward(request, response);
	    }
	  
	  private void listPeople(HttpServletResponse resp) throws IOException {

		  out = "List of Person ejbs:</br>";    
   
		    List<User> people = statelessBean.getAllUsers();
		    for (User person : people) {
		      out = out + "User retrieved: e-mail= " + person.getEmail() + " Password= " + person.getPassword() + "</br>";
		      	}

		    List<Registered> reg_people= statelessBean.getAllRegistered();
		    for (Registered person : reg_people) {
		    	out = out +	"Registered User retrieved: e-mail= " + (String) statelessBean.getUser(person.getUser_id()).getEmail()
			    		  + " Password= " + (String) statelessBean.getUser(person.getUser_id()).getPassword() 
			    		  + " Name= " + person.getName() + " Surname= " + person.getSurname() + " Birthdate= " + person.getBirthdate() + "</br>";
		    	} 
		    
		    List<Admin> adm_people= statelessBean.getAllAdmin();
		    for (Admin person : adm_people) {
		    	out = out +	"Admin User retrieved: e-mail= " + (String) statelessBean.getUser(person.getUser_id()).getEmail()
			    		  + " Password= " + (String) statelessBean.getUser(person.getUser_id()).getPassword() 
			    		  + " Name= " + person.getName() + " Surname= " + person.getSurname() + " Birthdate= " + person.getBirthdate() + "</br>";
		    	} 

/*		    List<RegisteredJoinUser> rju_people= statelessBean.getAllRegisteredJoinUser();
		    for (RegisteredJoinUser person : rju_people) {
			      out = out + "Registered User retrieved: e-mail= " + person.getEmail() + " Password= " + person.getPassword() + " Name= " + person.getName() + " Surname= " + person.getSurname() + " Birthdate= " + person.getBirthdate() + "</br>";
			  } 
*/	 
		  }
	  
	  private void displayPeople(HttpServletResponse resp) throws IOException {
	    PrintWriter writer = resp.getWriter();
	    writer.write("List of Person ejbs:\n");    
	    
	    List<User> people = statelessBean.getAllUsers();
	    for (User person : people) {
	      writer.write("User retrieved: e-mail= " + person.getEmail() + " Password= " + person.getPassword() + "\n");
	      }

	    List<Registered> reg_people= statelessBean.getAllRegistered();
	    for (Registered person : reg_people) {
			writer.write(		
	    			"Registered User retrieved: e-mail= " + (String) statelessBean.getUser(person.getUser_id()).getEmail()
		    		  + " Password= " + (String) statelessBean.getUser(person.getUser_id()).getPassword() 
		    		  + " Name= " + person.getName() + " Surname= " + person.getSurname() + " Birthdate= " + person.getBirthdate() + "\n");
		  } 
/*	    
	    List<RegisteredJoinUser> rju_people= statelessBean.getAllRegisteredJoinUser();
	    for (RegisteredJoinUser person : rju_people) {
		      writer.write("Registered User retrieved: e-mail= " + person.getEmail() + " Password= " + person.getPassword() + " Name= " + person.getName() + " Surname= " + person.getSurname() + " Birthdate= " + person.getBirthdate() + "\n");
		  } 
 */
	  }
}
