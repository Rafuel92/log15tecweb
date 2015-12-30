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
 * Servlet implementation class ModificaAutomezzoServerPage
 */
//@WebServlet("log15tecweb.ModificaAutomezzoServerPage")
public class ModificaAutomezzoServerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaAutomezzoServerPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	HttpSession session = request.getSession(false);
	if(session==null){
        RequestDispatcher rd=request.getRequestDispatcher("FineSessioneServlet");
	    rd.forward(request,response);
	}else {
	    response.setContentType("text/html");
		PrintWriter out = response.getWriter();
					  
		String data_acquisto=request.getParameter("data_acquisto");
		String targa=request.getParameter("targa");
		String modello = request.getParameter("modello");
		String id_automezzo = request.getParameter("id_automezzo");
		System.out.println("tet");
		System.out.println(data_acquisto);
		System.out.println(targa);
		System.out.println(modello);
		gestoreAutomezzi AutomezziManager = new gestoreAutomezzi();
		Boolean success = AutomezziManager.modificaAutomezzo(id_automezzo,targa,modello,data_acquisto);		
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
}
