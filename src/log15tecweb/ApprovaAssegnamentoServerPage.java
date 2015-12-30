package log15tecweb;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ApprovaAssegnamentoServerPage
 */
//@WebServlet("log15tecweb.ApprovaAssegnamentoServerPage")
public class ApprovaAssegnamentoServerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApprovaAssegnamentoServerPage() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String id_automezzo=request.getParameter("id_automezzo");
		String id_autista=request.getParameter("id_autista");
		String id_cliente=request.getParameter("id_cliente");
		String approvato = "1";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(cal.getTime());
		String formatted = format1.format(cal.getTime());
		System.out.println(formatted);
		System.out.println("superdata");
		String data = formatted;
		
		AssegnamentiDao Assegnamentidb= new AssegnamentiDao();
		Boolean success = Assegnamentidb.inserisciAssegnamento(approvato,data,id_cliente,id_automezzo,id_autista);		
		if(success){
			try{
				out.print("<div class='messages container ok'>Assegnamento Approvato correttamente</div>");
				RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
				rd.include(request,response);
			}catch (Exception e){
				out.println("PROBLEMI NELLA COSTRUZIONE DEL RESPONSO");
			}
		}
		else{
			out.print("<div class='messages container error'>Problema nell' approvazione</div>");
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
