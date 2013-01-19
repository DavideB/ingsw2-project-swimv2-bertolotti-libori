package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Skill;

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
 * Servlet implementation class ListSkills
 */

public class ListSkills extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StatelessEJBSkill statelessBean;
	private String out = new String();	 
	ArrayList<String> resultlist = new ArrayList<String>();
		
	  @Override
	public void init() throws ServletException {
	    try {
	    	
	      Context context = new InitialContext();
	      statelessBean = (StatelessEJBSkill) context.lookup("swim2/SkillManager/remote");
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
		  //request.getSession().setAttribute("resultlist", null);
		  //displaySkills(response);
		  listSkills(response);
//		  request.getSession().setAttribute("resultlist", resultlist);
		  request.getSession().setAttribute("availableSkills", statelessBean.getAllSkills());
		  //response.sendRedirect(response.encodeRedirectURL("services/nreg.jsp"));
		  request.getRequestDispatcher("WEB-INF/admin/skillmgmt.jsp").forward(request, response);
	    }
	  
	  private void listSkills(HttpServletResponse resp) throws IOException {

		  out = "List of NewSkillrequests ejbs:</br>"; 
		  List<Skill> skillList = statelessBean.getAllSkills();
		    for (Skill skill : skillList) {
		    	out = out + "Skill retrieved:  name= " + skill.getName() 
			    		  + " Lead_Id= " + skill.getLead_id()
			    		  + " Skill_Id = " + skill.getId()
			    		  + " Admin answer = " + skill.getAdminansw();
		      	}
		  }
	  
	  private void displaySkills(HttpServletResponse resp) throws IOException {
		  
	    PrintWriter writer = resp.getWriter();
	    writer.write("List of Skills ejbs:\n");    
	    
	    List<Skill> skillList = statelessBean.getAllSkills();
	    for (Skill skill : skillList) {
	      writer.write("Skill retrieved: name= " + skill.getName() + "\n");
	      }
	  }
}

