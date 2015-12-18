

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ModificaClienteServlet
 */
@WebServlet("/ModificaClienteServlet")
public class ModificaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificaClienteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	
	String nome=request.getParameter("nome");
	String sede_di_partenza=request.getParameter("sede_di_partenza");
	String sede_di_arrivo = request.getParameter("sede_di_arrivo");
	String id_user= request.getParameter("id_user");
	String id_client= request.getParameter("id_client");
	System.out.println("tet");
	ClienteDao client= new ClienteDao();
	Boolean success = client.modificaCliente(id_user,id_client,sede_di_partenza,sede_di_arrivo,nome);		
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
