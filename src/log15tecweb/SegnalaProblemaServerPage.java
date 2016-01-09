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
 * Servlet implementation class SegnalaProblemaServerPage
 */
@WebServlet("/SegnalaProblemaServerPage")
public class SegnalaProblemaServerPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SegnalaProblemaServerPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
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
			String id_assegnamento=request.getParameter("id_assegnamento");
			String testo_segnalazione=request.getParameter("testo_segnalazione");
			String usernameautista = request.getParameter("usernameautista");
			AssegnamentiDao Assegnamentidb= new AssegnamentiDao();
			Boolean success = Assegnamentidb.AggiornaAssegnamentoConSegnalazione(id_assegnamento,testo_segnalazione);		
			if(success){
				try{
					out.print("<div class='messages container ok'>Problema Segnalato Correttamente</div>");
					RequestDispatcher rd=request.getRequestDispatcher("client_autista.jsp?username="+usernameautista);
					rd.include(request,response);
				}catch (Exception e){
					out.println("PROBLEMI NELLA COSTRUZIONE DEL RESPONSOTestMessage"+e.getMessage());
				}
			}
			else{
				out.print("<div class='messages container error'>Problema non segnalato correttamente</div>");
				RequestDispatcher rd=request.getRequestDispatcher("client_autista.jsp?username="+usernameautista);
				rd.include(request,response);
			}
			
			out.close();
		}
		
	}

}
