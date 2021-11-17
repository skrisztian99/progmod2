<?php
// Change this to your connection info.
/*$DATABASE_HOST = 'localhost';
$DATABASE_USER = 'root';
$DATABASE_PASS = 'marigol1';
$DATABASE_NAME = 'vonatjegydb';
// Try and connect using the info above.
$con = mysqli_connect($DATABASE_HOST, $DATABASE_USER, $DATABASE_PASS, $DATABASE_NAME);
if (mysqli_connect_errno()) {
	// If there is an error with the connection, stop the script and display the error.
	exit('Failed to connect to MySQL: ' . mysqli_connect_error());
}
*/?>
<html>
<head>
    <title>Terminus - Könnyen, gyorsan</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
    <h2>Regisztráció</h2>

    <div class="container">
        <form method="post" action="register.php">
            <div class="input-group">
                <label>Vezetéknév</label>
                <input type="text" id="vezeteknev" required>
            </div>
            <div class="input-group">
                <label>Keresztnév</label>
                <input type="text" id="keresztnev" required>
            </div>
            <div class="input-group">
                <label>Telefonszám</label>
                <input type="number" id="telefonszam" required>
            </div>
            <div class="input-group">
                <label>Irányítószám</label>
                <input type="number" id="iranyitoszam" required>
            </div>
            <div class="input-group">
                <label>Város</label>
                <select name="varos" id="varos" required>
                    <option value="">Válasszon ki egy várost!</option>
                    <option value="budapest">Budapest</option>
                    <option value="debrecen">Debrecen</option>
                    <option value="szeged">Szeged</option>
                    <option value="miskolc">Miskolc</option>
                    <option value="pecs">Pécs</option>
                    <option value="gyor">Győr</option>
                    <option value="nyiregyhaza">Nyíregyháza</option>
                </select>
            </div>
            <div class="input-group">
                <label>Utca</label>
                <input type="text" id="utca" required>
            </div>
            <div class="input-group">
                <label>Házszám</label>
                <input type="text" id="hazszam" required>
            </div>
            <div class="input-group">
                <label>Születési idő</label>
                <input type="date" id="szulido" name="szulido" required>
            </div>
            <div class="input-group">
                <label>Kedvezmény</label>
                <select name="kedvezmeny" id="kedvezmeny" required>
                    <option value="">Válasszon ki egy kedvezményfajtát!</option>
                    <option id="nincs">Nincs Kedvezmény</option>
                    <option id="kozalkalmazott">Közalkalmazott</option>
                    <option id="diak">Diák</option>
                    <option id="kiskoru">Kiskorú(50%)</option>
                    <option id="65_feletti">65 év feletti(100%)</option>
                    <option id="felarva">Félárva(90%)</option>
                    <option id="fogyatek">Fogyatékkal élő személy(90%)</option>

                </select>
            </div>
            <div class="input-group">
                <label>E-mail cím</label>
                <input type="email" id="email" required>
            </div>
            <div class="input-group">
                <label>Jelszó</label>
                <input type="password" id="password_1" required>
            </div>
            <div class="input-group">
                <label>Jelszó megerősítése</label>
                <input type="password" id="password_2" required>
            </div>
            <div class="input-group">
                <button type="submit" class="btn" id="reg_user">Regisztráció</button>
            </div>

        </form>
        
    </div>
</body>

<footer>

</footer>

</html>