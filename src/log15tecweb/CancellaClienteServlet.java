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
 * Servlet implementation class CancellaClienteServlet
 */
@WebServlet("log15tecweb.CancellaClienteServlet")
public class CancellaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancellaClienteServlet() {
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
		String id_cliente = request.getParameter("id");
		ClienteDao Clientidbconn = new ClienteDao();
		Boolean success = Clientidbconn.cancellacliente(id_cliente);
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
