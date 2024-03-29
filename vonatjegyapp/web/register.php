<html>
<head>
    //A jelszó hossza max 45 lehet!!!
    <title>Terminus - Könnyen, gyorsan</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script> 

    <script>
        function ValidateEmail(uemail)
        {
          var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
          if(uemail.value.match(mailformat)){
              return true
          }
          else
          {
              alert("Helytelen email címet adott meg!");
              uemail.focus();
              return false;
          }
        }
        function passid_validation(passid,mx,my)
        {
            var passid_len = passid.value.length;
            if(passid_len === 0 ){
                alert("A jelszó nem lehet üres!");
                passid.focus();
                return false;
            }
            else if(passid_len >= my || passid_len < mx){
                alert("A jelszónak legalább"+mx+" hosszúnak kell lennie");
                passid.focus();
                return false;
            }
            return true;
        }
        function passid_match(pass1, pass2){
            if(pass1 !== pass2){
                alert("A két jelszó nem egyezik meg!");
                return false;
            }
            return true;
        }
        function onlyNumbers(uszam){
            var numbers = /^[0-9]*$/;
            if(uszam.value.match(numbers)){
                return true;
            }
            else{
                alert("Csakis telefonszámot írjon be!");
                uszam.focus();
                return false;
            }
        }
        function ZIPValidate(uirsz){
            var numbers = /^[0-9]*$/;
            var length = 4;
            if(uirsz.value.match(numbers) && uirsz.value.length === length){
                return true;
            }
            else{
                alert("Helytelen irányítószámot adott meg!");
                uszam.focus();
                return false;
            }
        }
        function NameValidation(uvezetek, ukereszt){
            var letters = /^[a-zA-Z]+$/;
            if(uvezetek.value.match(letters) && ukereszt.value.match(letters)){
                return true;
            }
            else
            {
                alert("A vezetéknév és keresztnév nem tartalmazhat számokat illetve különleges karaktereket!");
                uvezetek.focus();
                return false;
            }
        }
    function formValidation()
    {
        var uemail = document.getElementById("email");
        var passid_1 = document.getElementById("password_1");
        var passid_2 = document.getElementById("password_2");
        var uvezeteknev = document.getElementById("vezeteknev");
        var ukeresztnev = document.getElementById("keresztnev");
        var utelefon = document.getElementById("telefonszam");
        var uirsz = document.getElementById("telefonszam");
        var uvaros = document.getElementById("varos");
        var uutca = document.getElementById("utca");
        var uhazszam = document.getElementById("hazszam");
        var uszulido = document.getElementById("szulido");
        var ukedvezmeny = document.getElementById("kedvezmeny");
        if(ValidateEmail(uemail))
        {
            if(passid_validation(passid_1))
            {
                if(passid_match(passid_1, passid_2)){
                    if(NameValidation(uvezeteknev, ukeresztnev)){
                        if(onlyNumbers(utelefon)){
                            if(ZIPValidate(uirsz)){
                                var requestToSend = {"task": "createUtas", "email": uemail, "password_1": passid_1, "password_2": passid_2, "vezeteknev": uvezeteknev, "keresztnev": ukeresztnev, "telefon": utelefon, "irsz": uirsz, "varos": uvaros, "utca": uutca, "hazszam": uhazszam, "szulido": uszulido, "kedvezmeny": ukedvezmeny};
                                $.ajax({
                                   url: "/vonatjegyapp/UtasController",
                                   type: "POST",
                                   data: requestToSend
                                   success: function(response){
                                       alert("A regisztráció állapota: "+response.valasz);
                                       window.location.replace("login.php");
                                   }
                                           
                                   error: function(){
                                       alert("Hiba történt! az adatok feltöltése közben!");
                                   }
                                });
                            }
                        }
                    }
                }
            }
        }
    }
    
    </script>
</head>

<body>
    <h2>Regisztráció</h2>

    <div class="container">
        <form method="post" name="registration" onsubmit="return formValidation();">
            <div class="input-group">
                <label>Vezetéknév: </label>
                <input type="text" id="vezeteknev" required>
            </div>
            <div class="input-group">
                <label>Keresztnév:</label>
                <input type="text" id="keresztnev" required>
            </div>
            <div class="input-group">
                <label>Telefonszám:</label>
                <input type="number" id="telefonszam" required>
            </div>
            <div class="input-group">
                <label>Irányítószám:</label>
                <input type="number" id="irsz" required>
            </div>
            <div class="input-group">
                <label>Város:</label>
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
                <label>Utca:</label>
                <input type="text" id="utca" required>
            </div>
            <div class="input-group">
                <label>Házszám:</label>
                <input type="text" id="hazszam" required>
            </div>
            <div class="input-group">
                <label>Születési idő:</label>
                <input type="date" id="szulido" name="szulido" required>
            </div>
            <div class="input-group">
                <label>Kedvezmény:</label>
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
                <label>E-mail cím:</label>
                <input type="email" id="email" required>
            </div>
            <div class="input-group">
                <label>Jelszó:</label>
                <input type="password" id="password_1" required>
            </div>
            <div class="input-group">
                <label>Jelszó megerősítése:</label>
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