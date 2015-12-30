<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="views/theme/css/style.css" rel="stylesheet">
<title>Aggiunta Autista</title>
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
<form action="CreateAutistaServlet" class="form-signin" method="post">
        <h2 class="form-signin-heading">Aggiungi autista</h2>
        <input name="username" class="form-control" placeholder="Username" required autofocus>
        <input name="email" class="form-control" placeholder="E-mail" required>
        <input name="password"type="password" class="form-control" placeholder="Password" required>
        <input name="nome" class="form-control" placeholder="Nome" required>
        <input name="cognome" class="form-control" placeholder="Cognome" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Aggiungi</button>
      </form>
</body>
</html>
<% } %>