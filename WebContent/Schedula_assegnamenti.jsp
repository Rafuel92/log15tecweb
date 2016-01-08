<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="log15tecweb.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%! 
String printListaAutisti() {
		String output_assegnamenti;
		try {
			gestoreAutisti gest_autisti = new gestoreAutisti();
			output_assegnamenti = gest_autisti.Stampa_lista_autisti_per_assegnamenti();
			return output_assegnamenti;
		} catch (Exception e) {
			output_assegnamenti = "Non Disponibile " + e.getMessage();
			return output_assegnamenti;
		}
	}


%>
<head>
<script type="text/javascript">
      toggle = function(id) {
      var mydiv = document.getElementById(id);
      if (mydiv.style.display === 'block' || mydiv.style.display === '')
        mydiv.style.display = 'none';
      else
        mydiv.style.display = 'block';
      }
      pager = function(number,pager_class){    	  
	      var x = document.getElementsByClassName(pager_class);
	      var i;
	      for (i = 0; i < x.length; i++) {
	    	  console.log(x[i]);
	    	  x[i].className = x[i].className.replace('active','');
	      }
	      event.target.className = event.target.className + " active";
	      console.log(this.className);
	      var x = document.getElementsByClassName("paginatore-enabled");
	      var i;
	      for (i = 0; i < x.length; i++) {
	          x[i].style.display = "none";
	      }
	      var x = document.getElementsByClassName("result-counter-"+number);
	      var i;
	      for (i = 0; i < x.length; i++) {
	          x[i].style.display = "table-row";
	      }
      }
    </script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="views/theme/css/style.css" rel="stylesheet">
<title>Gestione Assegnamenti</title>
</head>
<body class="page-client-amministratore amministratore">
<%
   //allow access only if session exists
   String user = null;
   if(session.getAttribute("user") == null){
	     RequestDispatcher rd=request.getRequestDispatcher("FineSessioneServlet");
	     rd.forward(request,response);
	     System.err.println("SESSIONE NULLA");
   }else{ 
	     user = (String) session.getAttribute("user");
	     response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
         response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	     response.setDateHeader("Expires", 0); 
   }      
%>
<div class="colonna-5 amministratore-block assegnamenti-proposti">
          <h2 class="amministratore-block-heading">Lista Autisti</h2>
            <%=printListaAutisti() %>
        </div>
<%
   if(request.getAttribute("markup")!=null){
	   String markup=(String) request.getAttribute("markup");%>
	   <body class="page-client-amministratore amministratore">
       <div class="colonna-5 amministratore-block assegnamenti-proposti">
          <h2 class="amministratore-block-heading">Assegnamenti proposti</h2>
            <%=markup %>
        </div>
 <%}    %>

</body>
</html>