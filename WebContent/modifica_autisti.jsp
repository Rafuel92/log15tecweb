<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="views/theme/css/style.css" rel="stylesheet">
    <title>Modifica Cliente</title>
  </head>
  <body>
    <div class="container">
      <h2 class="page-title">Modifica Autista</h2>
      <form action="ModificaAutistaServlet" class="form-signin" method="post">
        <h2 class="form-signin-heading">Modifica Autista</h2>
		<input name="nome" class="form-control" placeholder="Nome" value="<%= request.getParameter("nome") %>" required autofocus/>
        <input name = "cognome" class="form-control" placeholder="Cognome" value="<%= request.getParameter("cognome") %>" required>
		<input type="hidden" name="id" value="<%= request.getParameter("id_user") %>" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">Invia</button>
      </form>