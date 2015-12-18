<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="views/theme/css/style.css" rel="stylesheet">
<title>Aggiunta Cliente</title>
</head>
<body>
<form action="CreateClienteServlet" class="form-signin" method="post">
        <h2 class="form-signin-heading">Aggiungi cliente</h2>
        <input name="username" class="form-control" placeholder="Username" required autofocus>
        <input name="email" class="form-control" placeholder="E-mail" required>
        <input name="password"type="password" class="form-control" placeholder="Password" required>
        <input name="nome" class="form-control" placeholder="Nome" required>
       <input name="sede_di_partenza" class="form-control" placeholder="Indirizzo del punto di partenza" required>
        <input name="sede_di_arrivo" class="form-control" placeholder="Indirizzo del punto di arrivo" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Aggiungi</button>
      </form>

</body>
</html>