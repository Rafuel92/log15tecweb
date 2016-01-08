<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="log15tecweb.*" %>
<%! 
	String printAutomezzi() {
		String toprint;
		try {
			gestoreAutomezzi gestautomezzi = new gestoreAutomezzi();
			toprint = gestautomezzi.PrintListaAutomezzi();
			return toprint;
		} catch (Exception e) {
			toprint = "Non Disponibile";
			return toprint;
		}
	}
	String printAutisti() {
	 String toprint;
			try {
				 gestoreAutisti gestautisti = new gestoreAutisti();
				 toprint = gestautisti.PrintListaAutisti();
				 return toprint;
				} catch(Exception e){
					 toprint = "Lista autisti non disponibile "+e.getMessage();
					 return toprint;
				}
	  }
	String printClienti() {
		   String toprint;
				try {
					 gestoreClienti gestclienti = new gestoreClienti();
					 toprint = gestclienti.PrintListaClienti();
					 return toprint;
					} catch(Exception e){
						 toprint = "ERRORE CLIENTI: "+e.getMessage();
						 return toprint;
					}
	      }
	String printAssegnamentiProposti() {
		String output_assegnamenti;
		try {
			GestoreAssegnamenti gest_assegnamenti = new GestoreAssegnamenti();
			output_assegnamenti = gest_assegnamenti.GeneratePossibiliAssegnamenti();
			return output_assegnamenti;
		} catch (Exception e) {
			output_assegnamenti = "Non Disponibile " + e.getMessage();
			return output_assegnamenti;
		}
	}
	String printAssegnamentiApprovati() {
		String output_assegnamenti;
		try {
			GestoreAssegnamenti gest_assegnamenti = new GestoreAssegnamenti();
			output_assegnamenti = gest_assegnamenti.PrintAssegnamentiApprovati();
			return output_assegnamenti;
		} catch (Exception e) {
			output_assegnamenti = "Non Disponibile " + e.getMessage();
			return output_assegnamenti;
		}		
	}
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="views/theme/css/style.css" rel="stylesheet">
    <title>Log15Tecweb | Dashboard Amministratore</title>
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
%>
    <div class="container contenitore-pagina-amministratore">
      <div class="row">
        <div class="title-wrapper colonna-12">
        	<h2 class="page-title">Dashboard Amministratore</h2>
        </div> 
        <div class="colonna-5 amministratore-block gestione-automezzi">
          <h2 class="amministratore-block-heading">Gestione Automezzi <a href="Aggiunta_automezzo.jsp">Aggiungi Automezzo</a></h2>
          <table class="amministratore-table table">
            <thead>
            <tr>
               <th>Targa Automezzo</th>
               <th>Stato Attuale</th>
               <th>Azioni</th>
            </tr>
            </thead>
              <%= printAutomezzi() %>
          </table>
        </div>
        <div class="colonna-5 amministratore-block ultimi-assegnamenti-approvati">
          <h2 class="amministratore-block-heading">Ultimi assegnamenti approvati<a href="Schedula_assegnamenti.jsp">Approva Assegnamenti</a></h2></h2>
          <table class="amministratore-table table">
            <thead>
            <tr>
               <th>Automezzo</th>
               <th>Cliente</th>
               <th>Autista</th>
               <th>Data</th>
            </tr>
            </thead>
            <%= printAssegnamentiApprovati() %>
          </table>
        </div>
        <div class="colonna-5 amministratore-block gestione-autisti">
          <h2 class="amministratore-block-heading">Gestione Autisti<a href="aggiungi_autista.jsp">Aggiungi Autista</a></h2>
          <table class="amministratore-table table">
            <thead>
            <tr>
               <th>Username</th>
               <th>Nome</th>
               <th>Cognome</th>
               <th>Modifica</th>
               <th>Elimina</th>
            </tr>
            </thead>
			<%= printAutisti() %>
            </tbody>
          </table>
        </div>
        <div class="colonna-5 amministratore-block gestione-clienti">
          <h2 class="amministratore-block-heading">Gestione Clienti<a href="aggiungi_cliente.jsp">Aggiungi Cliente</a></h2>
          <table class="amministratore-table table">
            <thead>
            <tr>
               <th>Nome</th>
               <th>Sede di Partenza</th>
               <th>Stato di Destinazione</th>
               <th>Azioni</th>
            </tr>
            </thead>
            <tbody>
			<%= printClienti() %>
            </tbody>
          </table>
        </div>
        
      <div class="mappa-situazione-attuale colonna-12">
		<% 
		String id_assegnamento = request.getParameter("id_assegnamento_map"); 
		GoogleApiConsumer gapi = new GoogleApiConsumer();
		String output_mappa;
		if(null != id_assegnamento){
		  output_mappa = gapi.GenerateIframeFromAddress(id_assegnamento);
		} else {
		  output_mappa = gapi.GenerateIframeFromAddress("last");
		}
		out.print(output_mappa);
        %>
	
      </div>
      </div>
      <footer>
        <form action="logout" method="POST" class="logout-form">
        	<input class="btn btn-danger" type="submit" value="Logout"/>
        </form>
      </footer>
  </body>
</html>
<%}%>