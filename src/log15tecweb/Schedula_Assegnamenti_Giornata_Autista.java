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
 * Servlet implementation class Schedula_Assegnamenti_Giornata_Autista
 */
@WebServlet("/Schedula_Assegnamenti_Giornata_Autista")
public class Schedula_Assegnamenti_Giornata_Autista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Schedula_Assegnamenti_Giornata_Autista() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String markup="";
		GestoreAssegnamenti gest_ass=new GestoreAssegnamenti();
		markup=gest_ass.GeneratePossibiliAssegnamenti2(request.getParameter("id_autista"),request.getParameter("data"));
		PrintWriter out=response.getWriter();
		out.println(markup);
		request.setAttribute("markup", markup);
		RequestDispatcher rd=request.getRequestDispatcher("Schedula_assegnamenti.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
