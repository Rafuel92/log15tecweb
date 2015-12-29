<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="views/theme/css/style.css" rel="stylesheet">
    <title>Log15Tecweb | Resoconto Autista</title>
    <script type="text/javascript">
      toggle = function(id) {
      var mydiv = document.getElementById(id);
      if (mydiv.style.display === 'block' || mydiv.style.display === '')
        mydiv.style.display = 'none';
      else
        mydiv.style.display = 'block';
      }
	  pager_hide = function() {
		  var x = document.getElementsByClassName("example");
		  var i;
		  for (i = 0; i < x.length; i++) {
		      x[i].style.backgroundColor = "red";
		  }
	  }
    </script>
  </head>
  <body class="page-client-autista autista">
    <div class="container contenitore-pagina-autista">
      <h2 class="page-title">Resoconto Autista</h2> 
      <div class="welcome-message">Benvenuto 
        <span class="username">Mario</span>
        <span class="password">Rossi</span>
      </div>
      <div class="notifiche">
        Notifiche <span class="number-notifications">1</span>
      </div>
      <div class="current-assegnamento">
        Tra <span class="current-assegnamento-minuti">10 minuti</span> a <span class="location-start">P.LE Tecchio</span>
        <div class="assegnamento">Per tratta 
          <span class="location-partenza">P.Le tecchio</span>
          <span class="locatin-destinazione">Viale indipendenza 10</span>
        </div>
      </div>
      <div class="assegnamenti-precendenti">
        <button class="btn assegnamenti-precedenti-trigger clickable" onclick="javascript:toggle('assegnamenti-storico-wrap');">Visualizza Storico Assegnamenti</button>
        <div style="display:none;" id="assegnamenti-storico-wrap">
          <div class="assegnamenti-inside success">Tratta effettuata con successo 
            <span class="location-partenza">P.Le tecchio</span>
            <span class="locatin-destinazione">Viale indipendenza 10</span>
          </div>
          <div class="assegnamenti-inside error">Un problema &egrave; stato segnalato per questa tratta 
            <span class="location-partenza">P.Le tecchio</span>
            <span class="locatin-destinazione">Viale indipendenza 10</span>
          </div>
        </div>
      </div>
      <div class="segnala-orario-effettivo-di-partenza segnalazione-autista-wrap">
        <button class="btn segnala-orario-di-partenza clickable" onclick="javascript:toggle('segnala-orario-di-partenza-form');">Segnala orario di partenza</button>
        <div style="display:none;" id="segnala-orario-di-partenza-form">
          FORM SEGNALA ORARIO PARTENZA
        </div>
      </div>
      <div class="segnala-orario-effettivo-di-arrivo segnalazione-autista-wrap">
        <button class="btn segnala-orario-di-arrivo clickable" onclick="javascript:toggle('segnala-orario-di-arrivo-form');">Segnala orario di arrivo</button>
        <div style="display:none;" id="segnala-orario-di-arrivo-form">
            FORM SEGNALA ORARIO ARRIVO
        </div>
      </div>
      <div class="segnala-problema segnalazione-autista-wrap">
        <button class="btn segnala-problema clickable" onclick="javascript:toggle('segnala-problema-form');">Segnala problema</button>
        <div style="display:none;" id="segnala-problema-form">
          FORM SEGNALA PROBLEMA FORM
        </div>
      </div>
      <div class="altre-info">
        <a class="segnala-problema" href="#">Altre info</a>
      </div>
      <div class="logout-wrap">
        <form action="logout" method="POST" class="logout-form">
        	<input type="submit" value="Logout"/>
        </form>
      </div>
    </div>
  </body>
</html>