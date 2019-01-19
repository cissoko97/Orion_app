<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/dist/bootstrap/css/bootstrap.min.css">
    <title>Account List</title>
</head>
<body>
<div class="container-fluid no-gutters p-0">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">ORION</a></nav>
</div>
<h1>Liste des comptes</h1>
<table class="table">
    <thead>
    <tr class="font-weight-bold">
        <td>Id</td>
        <td>Nom</td>
        <td>Prénom</td>
        <td>Adresse électronique</td>
        <td>Telephone</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${accounts}" var="account">
        <tr>
            <td>${account.id}</td>
            <td>${account.name}</td>
            <td>${account.surname}</td>
            <td>${account.email}</td>
            <td>${account.phone}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>