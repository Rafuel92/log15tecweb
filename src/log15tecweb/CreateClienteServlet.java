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
 * Servlet implementation class CreateClienteServlet
 */
@WebServlet("/CreateClienteServlet")
public class CreateClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		    String username=request.getParameter("username");
		    String email=request.getParameter("email");
		    String password=request.getParameter("password");
		    String nome=request.getParameter("nome");
		    String sede_di_partenza=request.getParameter("sede_di_partenza");
		    String sede_di_arrivo=request.getParameter("sede_di_arrivo");
		    String ragione_sociale=request.getParameter("ragione_sociale");
		    Cliente cliente=new Cliente(username, email, password, nome, null,sede_di_partenza,sede_di_arrivo);
		    PrintWriter out = response.getWriter();
		    if(cliente.inserimentocorretto){
		    	//Il cliente � stato aggiunto al DB
	            //APRI DASHBOARD AMMINISTRATORI
		    	out.println("<div class='messages container ok'>Cliente aggiunto</div>");
	        	RequestDispatcher rd=request.getRequestDispatcher("dashboard_amministratore.jsp");
				rd.include(request,response);
		    }else {
		        //Impossibile aggiungere l'autista al DB
		    	out.println("Impossibile aggiungere l'autista");
		    	RequestDispatcher rd=request.getRequestDispatcher("aggiungi_cliente.jsp");
				rd.include(request,response);
		    }}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
