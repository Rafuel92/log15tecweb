package log15tecweb;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * ANONOYMOUS HOME PAGE SERVLET
 */

//@WebServlet("log15tecweb.servlet1")
public class FirstServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String n=request.getParameter("username");
		String p=request.getParameter("userpass");
		LoginDao test = new LoginDao();
		
		ResultSet rs=test.validate(n, p);
		if(rs!=null){
			try{
			String usertype=rs.getString("usertype");
			request.setAttribute("usertype", usertype );
			request.setAttribute("username", n);
			RequestDispatcher rd=request.getRequestDispatcher("servlet3");
			rd.forward(request,response);
			}catch (Exception e){
				out.println("PROBLEMI CON USERTYPE");
			}
		}
		else{
			out.print("<div class='messages error'>Combinazione Username/Password non corretta</div>");
			RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request,response);
		}
		
		out.close();
	}

}
