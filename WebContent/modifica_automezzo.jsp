<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="views/theme/css/style.css" rel="stylesheet">
    <title>Modifica Automezzo</title>
  </head>
  <body>
  <%
   //allow access only if session exists
   String user = null;
   if(session.getAttribute("user") == null){
	     RequestDispatcher rd=request.getRequestDispatcher("FineSessioneServlet");
	     rd.forward(request,response);
	
   }else {
	     user = (String) session.getAttribute("user");
         response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
         response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
         response.setDateHeader("Expires", 0);
%>
    <div class="container">
      <h2 class="page-title">Modifica Automezzo</h2>
      <form action="modifica-automezzo-server" class="form-signin" method="post">
        <h2 class="form-signin-heading">Modifica Automezzo</h2>
		<input name="targa" class="form-control" value="<%= request.getParameter("targa") %>" required autofocus/>
        <input name = "modello" class="form-control" placeholder="Modello" value="<%= request.getParameter("modello") %>" required>
		<input type="text" class="form-control" required pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" name="data_acquisto"  value="<%= request.getParameter("data_acquisto") %>" placeholder="Data Acquisto">
		<input type="hidden" name="id_automezzo" value="<%= request.getParameter("id_automezzo") %>" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">Invia</button>
      </form>
    </div>
  </body>
</html>
<% } %>