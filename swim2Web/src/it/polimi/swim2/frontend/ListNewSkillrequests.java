package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.interfaces.StatelessEJBSkillrequest;
import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Admin;
import it.polimi.swim2.persistence.Helprequest;
import it.polimi.swim2.persistence.Newabilityrequest;
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
 * Servlet implementation class ListNewSkillrequests
 */
public class ListNewSkillrequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListNewSkillrequests() {
        super();
        // TODO Auto-generated constructor stub
    }
    private StatelessEJBSkillrequest statelessBeanSkillrequest;
    private StatelessEJBSkill statelessBeanSkill;
    private StatelessEJB statelessBean;
    
    private String out = new String();
    private String[] ability = new String[2];
    private ArrayList<String[]> resultlist = new ArrayList<String[]>();
		
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBeanSkillrequest = (StatelessEJBSkillrequest) context.lookup("swim2/SkillrequestManager/remote");
	      statelessBeanSkill = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
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
		listSkillrequests(response);
		request.getSession().setAttribute("resultlist", resultlist);
		request.getSession().setAttribute("out", out);
		request.getRequestDispatcher("services/list.jsp").forward(request, response);
		//request.getRequestDispatcher("services/skillrequestmgmt.jsp").forward(request, response);
		//return;
	    }
	  
	  private void listSkillrequests(HttpServletResponse resp) throws IOException {

		  resultlist.clear();
		  out = "List of NewSkillrequests ejbs:</br>";    
		  
		    List<Newabilityrequest> newabilityrequests = statelessBeanSkillrequest.getAllSkillrequests();
		    for (Newabilityrequest newabilityrequest : newabilityrequests) {
		    	
		    	ability[0] = (String) statelessBean.getUser(newabilityrequest.getMak_id()).getEmail();
		    	ability[1] = newabilityrequest.getMessage();
		    	resultlist.add(ability);
		    	
		    	out = out + "Newabilityrequest retrieved:  Make date= " + newabilityrequest.getMakdate() 
			    		  + " Message= " + newabilityrequest.getMessage()
			    		  + " Maker = " + (String) statelessBean.getUser(newabilityrequest.getMak_id()).getEmail() +  "</br>";
		      	}
		  }	 

	  private void displayHelprequests(HttpServletResponse resp) throws IOException {
		  
	    PrintWriter writer = resp.getWriter();
	    //writer.write("List of Helprequest ejbs:\n");    
	    
	    List<Newabilityrequest> newabilityrequests = statelessBeanSkillrequest.getAllSkillrequests();
	    for (Newabilityrequest newabilityrequest : newabilityrequests) {
	      writer.write("New Ability Request description retrieved: " + newabilityrequest.getMessage() + "\n");
	      }
	  }	

}
