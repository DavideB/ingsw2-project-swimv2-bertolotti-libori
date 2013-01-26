package it.polimi.swim2.frontend;

import it.polimi.swim2.interfaces.StatelessEJB;
import it.polimi.swim2.persistence.Registered;

import java.io.File;
import java.io.IOException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class UploadImage
 */
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StatelessEJB statelessBean;
	String baseDir = System.getProperty("jboss.server.home.dir")+"/deploy/ROOT.war/swim2images/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void init() throws ServletException {
    	super.init();
    	try {
	    	
  	      Context context = new InitialContext();
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
		throw new ServletException("Il metodo get non è supportato. Compila il form di login per ottenere l'accesso.");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recupera l'url dell'immagine
		String img = request.getParameter("newImage");
		//prepara il path nel quale salvare l'immagine
		String myPath = request.getSession().getAttribute("username")+"/";
		//controlla se c'è la cartella e nel caso la crea
		File folder = new File(baseDir+myPath);
		if (!folder.exists()) {
			try {
				if (!folder.mkdir()) {
					request.getSession().setAttribute("error","Impossibile effettuare l'upload dell'immagine: si consiglia di provare più tardi"+baseDir+myPath);
					response.sendRedirect("error.jsp");
					return;
				}
			} catch (Exception e) {
				request.getSession().setAttribute("error","Impossibile effettuare l'upload dell'immagine: si consiglia di provare più tardi");
				response.sendRedirect("error.jsp");
				return;
			}
		}
		System.err.println(myPath);
		//salva il file, la qui dimensione massima in byte è definita nel terzo campo parametro
		MultipartRequest multiReq = new MultipartRequest(request, baseDir+myPath, 4*1000*1000);
		//salva l'url nel database
		String[] tokens = multiReq.getOriginalFileName("newImage").split("/");
		String imgUrl = "localhost:8080/swim2images/"+myPath+tokens[tokens.length-1];
		Registered r = statelessBean.getUserData((String)request.getSession().getAttribute("username"));
		statelessBean.changeImg(r, imgUrl);
		response.sendRedirect("ManageUserData");
		return;
	}

}
