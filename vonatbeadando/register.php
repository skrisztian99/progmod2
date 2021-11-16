<?php include('server.php') ?>
<html>
<head>
    <title>Terminus - Könnyen, gyorsan</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body>
    <h2>Regisztráció</h2>

    <div class="container">
        <form method="post" action="register.php">
            <?php include('errors.php'); ?>
            <div class="input-group">
                <label>Vezetéknév</label>
                <input type="text" name="vezeteknev" value="<?php echo $vezeteknev; ?>">
            </div>
            <div class="input-group">
                <label>Keresztnév</label>
                <input type="text" name="keresztnév" value="<?php echo $keresztnev; ?>">
            </div>
            <div class="input-group">
                <label>Telefonszám</label>
                <input type="number" name="telefonszam" value="<?php echo $telszam; ?>">
            </div>
            <div class="input-group">
                <label>Irányítószám</label>
                <input type="number" name="iranyitoszam" value="<?php echo $iranyitoszam; ?>">
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
                <input type="text" name="utca" value="<?php echo $utca; ?>">
            </div>
            <div class="input-group">
                <label>Házszám</label>
                <input type="text" name="hazszam" value="<?php echo $hazszam; ?>">
            </div>
            <div class="input-group">
                <label>Születési idő</label>
                <input type="date" id="birthday" name="szulido"; ?>">
            </div>
            <div class="input-group">
                <label>Kedvezmény</label>
                <select name="kedvezmen" id="kedvezmeny" required>
                    <option value="">Válasszon ki egy kedvezményfajtát!</option>
                    <option value="nincs">Nincs Kedvezmény</option>
                    <option value="kozalkalmazott">Közalkalmazott</option>
                    <option value="diak">Diák</option>
                    <option value="kiskoru">Kiskorú(50%)</option>
                    <option value="65_feletti">65 év feletti(100%)</option>
                    <option value="felarva">Félárva(90%)</option>
                    <option value="fogyatek">Fogyatékkal élő személy(90%)</option>

                </select>
            </div>
            <div class="input-group">
                <label>E-mail cím</label>
                <input type="email" name="email" value="<?php echo $email; ?>">
            </div>
            <div class="input-group">
                <label>Jelszó</label>
                <input type="password" name="password_1">
            </div>
            <div class="input-group">
                <label>Jelszó megerősítése</label>
                <input type="password" name="password_2">
            </div>
            <div class="input-group">
                <button type="submit" class="btn" name="reg_user">Regisztráció</button>
            </div>

        </form>
        
    </div>
</body>

<footer>

</footer>

</html>