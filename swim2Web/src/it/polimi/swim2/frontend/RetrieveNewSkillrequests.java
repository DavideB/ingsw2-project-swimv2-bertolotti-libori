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
 * Servlet implementation class RetrieveNewSkillrequests
 */
public class RetrieveNewSkillrequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveNewSkillrequests() {
        super();
        // TODO Auto-generated constructor stub
    }
    private StatelessEJBSkillrequest statelessBeanSkillrequest;
    private StatelessEJBSkill statelessBeanSkill;
    private StatelessEJB statelessBean;
    
    private String out = new String();
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
		resultlist.clear();
		retrieveSkillrequests(response);
		request.getSession().setAttribute("resultlist", resultlist);
		//request.getSession().setAttribute("out", out);
		//request.getRequestDispatcher("services/list.jsp").forward(request, response);
		//request.getRequestDispatcher("services/skillrequestmgmt.jsp").forward(request, response);
		return;
	    }
	  
	  private void retrieveSkillrequests(HttpServletResponse resp) throws IOException {
		  
		  //out = "List of NewSkillrequests ejbs:</br>";    
		  
		  //List<Newabilityrequest> newabilityrequests = statelessBeanSkillrequest.getAllSkillrequests();
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		  Date mak_date = new Date();
	    	try {mak_date = sdf.parse("01/01/0001");} catch(Exception e) {e.printStackTrace();};
		    //List<Newabilityrequest> newabilityrequests = statelessBeanSkillrequest.getUnansweredSkillrequests(mak_date);
	    	List<Newabilityrequest> newabilityrequests = statelessBeanSkillrequest.getUnansweredSkillrequests();
		    for (Newabilityrequest newabilityrequest : newabilityrequests) {
		        String[] ability = new String[3];
		    	ability[0] = (String) statelessBean.getUser(newabilityrequest.getMak_id()).getEmail();
		    	ability[1] = newabilityrequest.getMessage();
		    	ability[2] = Integer.toString(newabilityrequest.getId());
		    	resultlist.add(ability);
		      	}
	  }	 

}
