<html>
    <head>
        <title> Terminus - Könnyen, gyorsan</title>
    </head>

    <body>
         <img class="logo" src="progmod.jpg" alt="Logó">
         <img class="fust" src="vonatbackground.jpg" alt="Füst">
       <p class="fomenu-string">FŐMENŰ</p>
            <ul>
                <li class="dropdown">
                        <a href="javascript:void(0)" class="dropbtn">Preferenciák</a>
                        <div class="dropdown-content">
                        <a href="Preferencia-modositas.html">Adatok módosítása</a>
                    
                        </div>
                
                <li><a href="Sajat-adatok.html">Saját adatok</a></li>
                <li><a href="Jegyvasarlas.html">Jegyvásárlás </a></li>
                <li><a href="Elozmenyek.html">Előzmények</a></li>

        
         </ul>

     <p class="kenyelem"><b> Ennyi kényelmet Ön is érdemel!</p>
     <p class="most-on-is">Most megteheti Ön is!</p>
   


   
    
    </body>

    <footer>

<!--CSS TIME-->
<style>
    head,body{
        background-color: rgb(26, 25, 37);
        
    }
    .most-on-is:hover{
        text-shadow: -20px 20px 40px black, -20px 20px 40px black;
        opacity: 0.85;

    }
    .most-on-is{
        font-size: 400%;
        margin-bottom: 100px;
        color: #886223;
        margin-left: 700px;
       
    }
    .kenyelem:hover {
    text-shadow: -20px 20px 40px black, -30px 30px 40px #192C41;
    opacity: 0.85;
    }
    .kenyelem{
       
        font-size: 400%;
        color: rgba(255,255,255,0.9);
      
    }
    
    .fomenu-string{
        padding: 0;
        margin: 0;
        position: absolute;
    }
    
.logo{
    opacity: 1;
    width: 298px;
    height: 243px;
    position: fixed;
  
}
.fust{
    opacity: 0.3;
  
    width: 100%;
    height: 300px;
}


ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  background-color: #F1B914;
  position: fixed;
}

li {
  float: left;
}

li a, .dropbtn {
  display: block;
  color: #885D17;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
  background-color:#192C41;
}

li.dropdown {
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #775723;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
  text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1;}

.dropdown:hover .dropdown-content {
  display: block;
}

</style>

    </footer>
</html>
