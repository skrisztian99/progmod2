<html>
<head>
    
    <img class="logo" src="progmod.jpg" alt="Logó">
    <img class="vonatgif" src="login.gif" alt="Vonat gif">
    <title>Terminus - Könnyen, gyorsan</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="login.css">
</head>
<script>
    var uemail = document.getElementById("email");
    var passid = document.getElementById("password");
    
    var requestToSend = {"task": "loginUtas", "email": uemail, "password": passid};
    
    $.ajax({
        url: "/vonatjegyapp/UtasController",
        type: "POST",
        data: requestToSend
        success: function(response){
            alert("A bejelentkezés "+response.valasz+" volt.");
            window.location.replace("vonatmain.html");
        }

        error: function(){
            alert("Hiba történt! az adatok feltöltése közben!");
        }
    </script>
<body>
    <h2>Írja be az email címét és jelszavát!</h2>
    <div class="container">
        <form class="form-signin">
            <h4 class="form-signin-heading"></h4>
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