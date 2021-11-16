<?php include('server.php') ?>
<!DOCTYPE html>
<html>
<head>
	<meta carset="utf-8">
	<title>Jegyvásárlás</title>
	<link rel="stylesheet" href="res/style.css">
</head>
<body>
<h1>Jegyvásárlás</h1>
<form>  
	Név:
	<input type="text" name="Nev" size="20" value="<?php echo $vezeteknev ?> <?php echo $keresztnev ?>">  
	<br> <br>  
	Indulás:
	<select class="combo">
		<option value="Válaszd ki">Válaszd ki....</option>
		<option value="Budapest">Budapest</option>
		<option value="Pécs">Pécs</option>
		<option value="Dombóvár">Dombóvár</option>
		<option value="Kaposvár">Kaposvár</option>
		<option value="Szekszárd">Szekszárd</option>
		<option value="Siófok">Siófok</option>
		<option value="Zamárdi">Zamárdi</option>
		<option value="Balatonfüred">Balatonfüred</option>
		<option value="Veszprém">Veszprém</option>
		<option value="Nagydorog">Nagydorog</option></select>
	Érkezés:
	<select class="combo">
		<option value="Válaszd ki">Válaszd ki....</option>
		<option value="Budapest">Budapest</option>
		<option value="Pécs">Pécs</option>
		<option value="Dombóvár">Dombóvár</option>
		<option value="Kaposvár">Kaposvár</option>
		<option value="Szekszárd">Szekszárd</option>
		<option value="Siófok">Siófok</option>
		<option value="Zamárdi">Zamárdi</option>
		<option value="Balatonfüred">Balatonfüred</option>
		<option value="Veszprém">Veszprém</option>
		<option value="Nagydorog">Nagydorog</option></select>
		KM:
	<br><br>  
	Időpont:
	<input type="date" value="2021-11-16">
	Kedvezmény:
	<FONT COLOR="RED"><del>Próba ár</del></FONT>
	Ár:
	
	<br><br> 
	Fizetés módja:
	<select class="combo">
		<option value="Készpénz">Készpénz</option>
		<option value="Bankkártya">Bankkártya</option>
		<option value="Készpénz">Utalás</option></select>
	<br><br>
	<button type="Gomb">Vásárlás</button>
	<p id="date"></p>
</form> 
<p></p>

</body>
</html>