<!--//UTILISATION DE CSS -->
<link rel="stylesheet" href="./style/gestionlogin.css"  />

<!--DEBUT CODE php -->
<?php

//FAIRE RECOURT LA PAGE 'Create ......' UNE FOIS  
include('Create DB&Tables.php');
	
//DÉMARRE UNE NOUVELLE SESSION OU REPREND UNE SESSION EXISTANTE 
session_start();

//RECUPERATION DES INFOS ET CREATION DE VARIABLES
	if(isset($_POST['username']))
	{
	$username = $_POST['username'];
	$password = $_POST['password'];

	//CONNEXION A LA BASE DE DONNEE ET UTILISATION D'UNE REQUETE DE SELECTION POUR LE TABLEAU 'users' 
	mysql_connect("localhost", "root", "");
	mysql_select_db("dbequipe8");
	$query="select * from users";
	$result = mysql_query($query) 
	or die("PAS DE BASE DE DONNEES".mysql_error());
	$num_row = mysql_num_rows($result);
	
	//echo $num_row."<br>";//juste un TEST
	
	//CREATION ET INITIALISATION D'UNE VARIABLE POUR VERIFIER L'AUTHENTIFICATION PROPRE 
	$flag=0;
	
		while($row=mysql_fetch_array($result))
		{
		$dusers=$row['username']; 
		$dpass=$row['password'];
		// COMPARAISON DES DONNEEES ENTREES AU CLAVIER AVEC LES DONNEES DE LA BASE DE DONNEES

//ON PEUT UTILISER md5()pour encrypter le mot de passe
	if($username == $dusers && $password==$dpass)
	{   
		$flag=1;
		$_SESSION['Log'] = $dusers; 

		//REDIRECTION+PASSATION DE VARIABLE '$username' DANS 'name' VERS 'gcatalogue.php'
		header("Location:gcatalogue.php?name=$username");
	}
}
	//IDENTIFICATION ERRONEE(MOT DE PASSE.......ERRREUR)
	if($flag==0)
	{
	echo "<font color=red>VEUILLEZ ENTRER DES INFORMATIONS DE CONNEXIONS VALABLES...</font>";
	
	}

}

?>

<form action="#" method =post>
<fieldset>
<legend>INFORMATIONS DE CONNEXION</legend>
<table width=650>
<tr>
<td height="38">Utilisateur</td>
<td><input type=text name=username></td>
</tr>
<tr>
<td height="29"><label for='mot de passe' id=mdplogin>Mot de Passe</label></td>
<td><input type=password name=password></td>
</tr>
<tr>
<td colspan=2><p>&nbsp;
  </p>
  <p>
    <input type=submit name=submit id=value="Se connecter">
  </p></td>
</tr>
</table>
</fieldset>
</form>