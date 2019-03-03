<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/dist/bootstrap/css/bootstrap.min.css">
    <title>Connexion</title>
</head>
<body>
<div class="container-fluid">
    <form method="post" class="col-4 offset-lg-4 mt-5 border py-3">
        <h3 class="col font-weight-bold">Connexion</h3>
        <hr>
        <div class="col-12 form-group">
            <label for="email">Adresse électronique</label>
            <input id="email" name="email" value="${model.email}" class="form-control">
            <span class="text-danger font-italic">${errors.email}</span>
        </div>
        <div class="col-12 form-group">
            <label for="password">Mot de passe</label>
            <input type="password" id="password" name="password" value="${model.password}" class="form-control">
            <span class="text-danger font-italic">${errors.password}</span>
        </div>
        <div class="col text-right">
            <button class="btn btn-info">SE CONNECTER</button>
        </div>
    </form>
</div>
</body>
</html>