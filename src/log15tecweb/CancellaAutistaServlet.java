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
 * Servlet implementation class CancellaAutistaServlet
 */
//@WebServlet("log15tecweb.CancellaAutistaServlet")
public class CancellaAutistaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaAutistaServlet() {
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
		String id_autista = request.getParameter("id_autista");
		String id=request.getParameter("id");
		AutistaDao Autistidbconn = new AutistaDao();
		Boolean success = Autistidbconn.cancellaautista(id,id_autista);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(success){
			try{
				out.print("<div class='messages container ok'>Cancellazione andata a buon fine</div>");
				RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
				rd.include(request,response);
			}catch (Exception e){
				out.println("PROBLEMI NELLA COSTRUZIONE DEL RESPONSO");
			}
		}
		else{
			out.print("<div class='messages container error'>Problema nella cancellazione</div>");
			RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
			rd.include(request,response);
		}
		
		out.close();
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
