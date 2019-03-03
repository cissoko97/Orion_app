<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="/dist/bootstrap/css/bootstrap.min.css">
    <title>Registration</title>
</head>
<body>
<div class="container-fluid no-gutters p-0 container-fluid">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">Inscription</a>
    </nav>
    <div class="col-3 offset-8">
        <div class="card">
            <div class="card-body">
                <div class="card-header">Régistration</div>
                <form method="post" action="/registration">
                    <div class="form-group">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" name="name" id="name" value="${model.name}"
                               placeholder="Your name">
                        <span class="text-danger font-italic">${errors.name}</span>
                    </div>
                    <div class="form-group">
                        <label for="surname">Surname</label>
                        <input type="text" class="form-control" name="surname" id="surname" value="${model.surname}"
                               placeholder="Your surname">
                        <span class="text-danger font-italic">${errors.surname}</span>
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input type="text" name="phone" id="phone" class="form-control" value="${model.phone}"
                               placeholder="Your phone number">
                        <span class="text-danger font-italic">${errors.phone}</span>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="email" id="email" value="${model.email}"
                               placeholder="Your email">
                        <span class="text-danger font-italic">${errors.email}</span>
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" name="password" id="password"
                               value="${model.password}" placeholder="Your password">
                        <span class="text-danger font-italic">${errors.password}</span>
                    </div>
                    <div class="form-group">
                        <label for="password_match">Password confirmation</label>
                        <input type="password" class="form-control" name="passwordMatch" value="${model.passwordMatch}"
                               id="password_match" placeholder="Your password">
                        <span class="text-danger font-italic">${errors.passwordMatch}</span>
                    </div>
                    <button type="submit" class="btn btn-primary">Sign in</button>
                </form>
            </div>
        </div>

    </div>
</div>
<script src="/dist/js/jquery-3.3.1.min.js"></script>
<script src="/dist/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>