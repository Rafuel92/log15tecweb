package log15tecweb;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CreateAutistaServlet
 */
//@WebServlet("log15tecweb.CreateAutistaServlet")
public class CreateAutistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAutistaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	
		HttpSession session = request.getSession(false);
	  if(session==null){
		     RequestDispatcher rd=request.getRequestDispatcher("FineSessioneServlet");
		     rd.forward(request,response);
	  }else {
		String username=request.getParameter("username");
	    String email=request.getParameter("email");
	    String password=request.getParameter("password");
	    String nome=request.getParameter("nome");
	    String cognome=request.getParameter("cognome");
	    Autista autista=new Autista(username, email, password, nome, cognome);
	    PrintWriter out = response.getWriter();
	    if(autista.inserimentocorretto){
	    	//L'autista � stato aggiunto al DB
            //APRI DASHBOARD AMMINISTRATORI
	    	out.println("<div class='messages container ok'>Autista aggiunto</div>");
        	RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
			rd.include(request,response);
	    }else {
	        //Impossibile aggiungere l'autista al DB
	    	out.println("Impossibile aggiungere l'autista");
	    	RequestDispatcher rd=request.getRequestDispatcher("aggiungi_autista.jsp");
			rd.include(request,response);
	    }
	  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
