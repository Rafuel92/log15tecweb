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
    <div class="container">
      <h2 class="page-title">Modifica Automezzo</h2>
      <form action="modifica-automezzo-server" class="form-signin" method="post">
        <h2 class="form-signin-heading">Modifica Automezzo</h2>
		<input name="targa" class="form-control" value="<%= request.getParameter("targa") %>" required autofocus/>
        <input name = "data_acquisto" class="form-control" placeholder="Data Acquisto" value="<%= request.getParameter("data_acquisto") %>" required>
        <input name = "modello" class="form-control" placeholder="Modello" value="<%= request.getParameter("modello") %>" required>
		<input type="hidden" name="id_automezzo" value="<%= request.getParameter("id_automezzo") %>" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">Invia</button>
      </form>
    </div>
  </body>
</html>