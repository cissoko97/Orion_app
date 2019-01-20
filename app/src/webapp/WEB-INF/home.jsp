<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/dist/bootstrap/css/bootstrap.min.css">
    <title>Home</title>
</head>
<body>
<div class="container-fluid no-gutters p-0">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">ORION</a>
        <div style="flex: 1 1 auto;"></div>
        <div class="text-light">
            <c:if test="${ !empty sessionScope.account}">
                ${sessionScope.account.name} ${sessionScope.account.surname}
                <form class=" ml-3 d-inline" method="post" action="/authentication/logout">
                    <button class="btn btn-danger">Se Déconnecter</button>
                </form>
            </c:if>
        </div>
    </nav>
</div>
<script src="/dist/js/jquery-3.3.1.min.js"></script>
<script src="/dist/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>