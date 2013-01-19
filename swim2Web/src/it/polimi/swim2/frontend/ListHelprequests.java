package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Registered;
import it.polimi.swim2.persistence.Skill;
import it.polimi.swim2.persistence.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
 * Servlet implementation class ListHelprequests
 */
public class ListHelprequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListHelprequests() {
        super();
        // TODO Auto-generated constructor stub
    }

    private StatelessEJBHelprequest statelessBeanHelprequest;
    private StatelessEJBSkill statelessBeanSkill;
    private StatelessEJB statelessBean;
	 
    private String out = new String();
    
    ArrayList<Helprequest> resultlist = new ArrayList<Helprequest>();
		
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBeanHelprequest = (StatelessEJBHelprequest) context.lookup("swim2/HelprequestManager/remote");
	      statelessBeanSkill = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
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
		  init();
		  listHelprequest(response);
		  request.getSession().setAttribute("out", out);
		  request.getRequestDispatcher("services/list.jsp").forward(request, response);
	    }
	  
	  private void listHelprequest(HttpServletResponse resp) throws IOException {

		  out = "List of Helprequest ejbs:</br>";    
		  
		    List<Helprequest> helprequests = statelessBeanHelprequest.getAllHelprequests();
		    for (Helprequest helprequest : helprequests) {
		      out = out + "Helprequest retrieved:  Sent date= " + helprequest.getSentdate() 
		    		  + " Description= " + helprequest.getMessage() 
		    		  + " Skill#= " + helprequest.getSkillId()
		    		  + " Skill Name= " + statelessBeanSkill.getSkill(helprequest.getSkillId()).getName() 
		    		  + " Sender= " + (String) statelessBean.getUser(helprequest.getSen_id()).getEmail() +  "</br>";
		      	}
		  }	 
  
	  private void displayHelprequests(HttpServletResponse resp) throws IOException {
		  
	    PrintWriter writer = resp.getWriter();
	    //writer.write("List of Helprequest ejbs:\n");    
	    
	    List<Helprequest> helprequestList = statelessBeanHelprequest.getAllHelprequests();
	    for (Helprequest helprequest : helprequestList) {
	      writer.write("Request for help description retrieved: " + helprequest.getMessage() + "\n");
	      }
	  }

}
