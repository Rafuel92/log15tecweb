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
 * Servlet implementation class WelcomeServlet
 */
//@WebServlet("log15tecweb.WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WelcomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
        String usertype=(String) request.getAttribute("usertype");
        String username=(String) request.getAttribute("username");
    	HttpSession session=request.getSession();
    	session.setAttribute("user", username);
    	session.setMaxInactiveInterval(100); //Numero di secondi dopo il quale la sessione scadr�
        if(usertype.compareToIgnoreCase("a")==0){
        	//APRI DASHBOARD AMMINISTRATORI
        	response.sendRedirect("dashboard_amministratore.jsp");
        	/*RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
			rd.include(request,response);*/
        }
        if(usertype.compareToIgnoreCase("d")==0){
        	//APRI PAGINA AUTISTI
        	RequestDispatcher rd=request.getRequestDispatcher("client_autista.jsp?username="+username);

			rd.include(request,response);
        }
        if(usertype.compareToIgnoreCase("c")==0){
            PrintWriter out = response.getWriter();
            out.println("Funzionalita' cliente non implementata");
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

