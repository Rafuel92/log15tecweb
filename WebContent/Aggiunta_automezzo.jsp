<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="views/theme/css/style.css" rel="stylesheet">
    <title>Aggiunta Automezzo</title>
  </head>
  <body>
    <div class="container">
      <h2 class="page-title">Aggiunta Automezzo</h2>
      <form action="aggiunta-automezzo-server" class="form-signin" method="post">
        <h2 class="form-signin-heading">Aggiunta Automezzo</h2>
        <input name="targa" class="form-control" placeholder="Targa" required autofocus>
        <input name = "modello" class="form-control" placeholder="Modello" required>
		<input name = "data_acquisto" class="form-control" placeholder="Data Acquisto" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Invia</button>
      </form>
    </div>
  </body>
</html>