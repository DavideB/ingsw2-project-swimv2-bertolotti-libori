package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJBHelprequest;
import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.interfaces.StatelessEJBSkill;
import it.polimi.swim2.persistence.Registered;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
//import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelprequestCreation
 */
public class SendHelpRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	private StatelessEJBSkill statelessBeanSkill;
	private StatelessEJBHelprequest statelessBeanHelprequest;

	@Override
	public String getServletInfo() {
		return "This servlet handles the requests to create new requests for help";
	}

	@Override
	public void init() throws ServletException {
		try {

			Context context = new InitialContext();
			statelessBean = (StatelessEJB) context
					.lookup("swim2/UserManager/remote");
			statelessBeanSkill = (StatelessEJBSkill) context
					.lookup("swim2/SkillManager/remote");
			statelessBeanHelprequest = (StatelessEJBHelprequest) context
					.lookup("swim2/HelprequestManager/remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendHelpRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Registered r = (Registered) request.getSession().getAttribute(
				"userData");
		// controlla che l'accesso sia stato effettuato
		if (r == null) {
			request.getSession().setAttribute("error",
					"Devi prima effettuare l'accesso!");
			response.sendRedirect("access/home.jsp");
			return;
		}
		String email = (String) request.getSession().getAttribute("username");
		String skillname = request.getParameter("skillname");
		String descr = request.getParameter("message");
		boolean isForFriend = Boolean.parseBoolean(request.getParameter("isForFriend"));
		byte b = (byte) (isForFriend ? 1 : 0);
		int sen_id = statelessBean.getUser(email).getId();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date sentdate = Calendar.getInstance().getTime();

		int skillId = statelessBeanSkill.getSkill(skillname).getId();

		if // registrazione Helprequest ok
			// ( statelessBeanHelprequest.createHelprequest(ans_id, ansdate,
			// isForFriend, message, sen_id, sentdate, skillId) )
		(statelessBeanHelprequest.createHelprequest(b, descr, sen_id,
				sentdate, skillId)) {
			response.sendRedirect("ManageHelpRequest");
		}

		else
		// /registrazione utente fallita
		{
			request.getSession()
					.setAttribute(
							"error",
							"impossibile inviare la richiesta d'aiuto. Si prega di verificare i dati inseriti.\n");
			response.sendRedirect("error.jsp");
		}
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
