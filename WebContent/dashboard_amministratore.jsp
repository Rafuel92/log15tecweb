<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.lang.reflect.*" %>
<%! String printAutomezzi() {
		String toprint;
		try {
			Class c = Class.forName("gestoreAutomezzi");
			Object obj = c.newInstance();
			Method m2 = c.getDeclaredMethod("PrintListaAutomezzi", null);
			Object output_automezzi = m2.invoke(obj, null);
			toprint = (String) output_automezzi;
			return toprint;
		} catch (Exception e) {
			toprint = "Non Disponibile";
			return toprint;
		}
	}
	String printAutisti() {
	 String toprint;
			try {
				 Class c = Class.forName("gestoreAutisti");
				 Object obj  = c.newInstance();
				 Method m2 = c.getDeclaredMethod("PrintListaAutisti",null);
				 Object output_autisti = m2.invoke(obj,null);
				 toprint = (String) output_autisti;
				 return toprint;
				} catch(Exception e){
					 toprint = "PROBLEMA";
					 return toprint;
				}
	  }
	
	String printClienti() {
		   String toprint;
				try {
					 Class c = Class.forName("gestoreClienti");
					 Object obj  = c.newInstance();
					 Method m2 = c.getDeclaredMethod("PrintListaClienti",null);
					 Object output_clienti = m2.invoke(obj,null);
					 toprint = (String) output_clienti;
					 return toprint;
					} catch(Exception e){
						 toprint = "ERRORE CLIENTI: "+e.getMessage();
						 return toprint;
					}
	      }
	String printAssegnamentiProposti() {
		String output_assegnamenti;
		try {
			Class c = Class.forName("GestoreAssegnamenti");
			Object obj = c.newInstance();
			Method m2 = c.getDeclaredMethod("GeneratePossibiliAssegnamenti", null);
			Object output_assegnamenti_ottenuto = m2.invoke(obj, null);
			output_assegnamenti = (String) output_assegnamenti_ottenuto;
			return output_assegnamenti;
		} catch (Exception e) {
			output_assegnamenti = "Non Disponibile " + e.getMessage();
			return output_assegnamenti;
		}
	}
	String printAssegnamentiApprovati() {
		String output_assegnamenti;
		try {
			Class c = Class.forName("GestoreAssegnamenti");
			Object obj = c.newInstance();
			Method m2 = c.getDeclaredMethod("PrintAssegnamentiApprovati", null);
			Object output_assegnamenti_ottenuto = m2.invoke(obj, null);
			output_assegnamenti = (String) output_assegnamenti_ottenuto;
			return output_assegnamenti;
		} catch (Exception e) {
			output_assegnamenti = "Non Disponibile " + e.getMessage();
			return output_assegnamenti;
		}		
	}
	String printMappa() {
		String output_mappa;
		try {
			Class c = Class.forName("GoogleApiConsumer");
			Object obj = c.newInstance();
			Method m2 = c.getDeclaredMethod("GenerateIframeFromAddress", null);
			Object output_mappa_ottenuto = m2.invoke(obj, null);
			output_mappa = (String) output_mappa_ottenuto;
			return output_mappa;
		} catch (Exception e) {
			output_mappa = "Non Disponibile " + e.getMessage();
			return output_mappa;
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
    </script>
  </head>
  <body class="page-client-amministratore amministratore">
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
        <div class="colonna-5 amministratore-block assegnamenti-proposti">
          <h2 class="amministratore-block-heading">Assegnamenti Proposti</h2>
          <table class="amministratore-table table">
            <thead>
            <tr>
               <th>Automezzo</th>
               <th>Autista</th>
               <th>Cliente</th>
               <th>Data</th>
               <th>Azioni</th>
            </tr>
            </thead>
            <tbody>
            <%=printAssegnamentiProposti() %>
            </tbody>
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
        <div class="colonna-5 amministratore-block ultimi-assegnamenti-approvati">
          <h2 class="amministratore-block-heading">Ultimi assegnamenti approvati</h2>
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
      <div class="mappa-situazione-attuale colonna-12">
			<%= printMappa() %>
      </div>
      </div>
      <footer>
        <form action="logout" method="POST" class="logout-form">
        	<input class="btn btn-danger" type="submit" value="Logout"/>
        </form>
      </footer>
  </body>
</html>