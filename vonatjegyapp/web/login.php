<?php

    ob_start();
    session_start();

?>

<html>
<head>
    <title>Terminus - Könnyen, gyorsan</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
    <h2>Írja be az email címét és jelszavát!</h2>
    <?php 
        $msg = '';

        if(isset($_POST['login']) && !empty($_POST['email']) && !empty($_POST['password']))
         {
            if($_POST['email'] == 'kamu email' && $_POST['password'] == '1234') {
                $_SESSION['valid'] = true;
                $_SESSION['username'] =  'kamu username';
            }
            else {
                $msg = 'Az email cím vagy a jelszó hibás!';
            }
        }
    ?>

    <div class="container">
        <form class="form-signin" role="form" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']); ?>" method="post">
            <h4 class="form-signin-heading"><?php echo $msg ?></h4>
            <input type="text" class="form-control" name="email" placeholder="Az ön email címe" required autofocus>
            <input type="password" class="form-control" name="password" placeholder="Az ön jelszava" required>
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