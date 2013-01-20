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
 * Servlet implementation class RetrieveSkills
 */

public class RetrieveSkills extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StatelessEJBSkill statelessBean;
	 
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
		  resultlist.clear();
		  retrieveSkills(response);
		  request.getSession().setAttribute("resultlist", resultlist);
		  return;
	    }
	  
	  private void retrieveSkills(HttpServletResponse resp) throws IOException {
		  
		  List<Skill> skillList = statelessBean.getAllSkills();
		    for (Skill skill : skillList) {
		      resultlist.add(skill.getName());
		      	}
		  }
}

