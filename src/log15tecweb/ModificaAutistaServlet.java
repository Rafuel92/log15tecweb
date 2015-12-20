package log15tecweb;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaAutistaServlet
 */
//@WebServlet("log15tecweb.ModificaAutistaServlet")
public class ModificaAutistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaAutistaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	String nome=request.getParameter("nome");
	String cognome=request.getParameter("cognome");
	String id_user= request.getParameter("id");
	AutistaDao autista= new AutistaDao();
	Boolean success = autista.modificaAutista(id_user,nome,cognome);		
	if(success){
		try{
			out.print("<div class='messages container ok'>Modifica andata a buon fine</div>");
			RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
			rd.include(request,response);
		}catch (Exception e){
			out.println("PROBLEMI NELLA COSTRUZIONE DEL RESPONSO");
		}
	}
	else{
		out.print("<div class='messages container error'>Problema nella modifica</div>");
		RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
		rd.include(request,response);
	}
	
	out.close();
    }

}
