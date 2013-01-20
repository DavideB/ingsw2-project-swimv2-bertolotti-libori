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
public class RetrieveHelprequests extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetrieveHelprequests() {
        super();
        // TODO Auto-generated constructor stub
    }

    private StatelessEJBHelprequest statelessBeanHelprequest;
    private StatelessEJBSkill statelessBeanSkill;
    private StatelessEJB statelessBean;
    
    private String[] h_request = new String[8];
    private ArrayList<String[]> resultlist = new ArrayList<String[]>();
    //ArrayList<Helprequest> resultlist = new ArrayList<Helprequest>();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
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
		  resultlist.clear();
		  requestHelprequest(response);
		  //request.getSession().setAttribute("out", out);
		  //request.getRequestDispatcher("services/list.jsp").forward(request, response);
		  return;
	    }
	  
	  private void requestHelprequest(HttpServletResponse resp) throws IOException {

		    List<Helprequest> helprequests = statelessBeanHelprequest.getAllHelprequests();
		    for (Helprequest helprequest : helprequests) {
		    	
		    	h_request[0] = helprequest.getMessage();
		    	h_request[1] = Integer.toString(helprequest.getAns_id());
		    	h_request[2] = sdf.format(helprequest.getAnsdate());
		    	h_request[3] = Integer.toString(helprequest.getId());
		    	h_request[4] = Integer.toString(helprequest.getIsForFriend());
		    	h_request[5] = Integer.toString(helprequest.getSen_id());
		    	h_request[6] = sdf.format(helprequest.getSentdate());
		    	h_request[7] = Integer.toString(helprequest.getSkillId());
		    }
		    resultlist.add(h_request);
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
