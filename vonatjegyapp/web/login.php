<html>
<head>
    <title>Terminus - Könnyen, gyorsan</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
    <h2>Írja be az email címét és jelszavát!</h2>
    <div class="container">
        <form class="form-signin" role="form" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="post">
            <h4 class="form-signin-heading"><?php echo $msg ?></h4>
            <input type="text" class="form-control" id="email" placeholder="Az ön email címe" required autofocus>
            <input type="password" class="form-control" id="password" placeholder="Az ön jelszava" required>
            <button class="btn btn-lg btn primary btn-block" type="submit" name="login">Bejelentkezés</button>
            <p>Még nem regisztrált?
                <a href="register.php">Kattintson ide!</a>
            </p>
        </form>
    </div>
</body>

<footer>

</footer>

</html>>