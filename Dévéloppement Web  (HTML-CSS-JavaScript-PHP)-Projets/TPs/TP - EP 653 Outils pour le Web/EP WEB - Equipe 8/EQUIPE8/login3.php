<?php

//DÉMARRE UNE NOUVELLE SESSION OU REPREND UNE SESSION EXISTANTE 
session_start();
if(isset($_SESSION['Log']))
{
				//echo "<br>$_GET[class]<br>$_GET[password]";
				$nvisits = 1;
				if (isset($_COOKIE["nvisits"])) 
				{
					
				$nvisits = (int)$_COOKIE["nvisits"];	
				}
				//DÉFINITION D' UN COOKIE QUI SERA ENVOYÉ AVEC LE RESTE DES EN-TÊTES... 
							//3600 est 60s*60heure mais moins d'une heure
				setcookie("nvisits", $nvisits + 1, time() -3600);
				echo "<h2 align='right'>UTILISATEUR: $_SESSION[Log]</h2>";  
				echo"<p align='right'><a href = Adminconfig.php>Parametres</a>  <a href = Admindeconnexion.php>Deconnexion</a></p>";
				//echo"<p align='right'><a href = Admindeconnexion.php>Deconnexion</a></p>";
				//echo "Nombre de visites sur cette page  $nvisits."; //JUSTE UN TEST

}
else
{  
//SECURISATION DES SESSIONS ET COMPTES DONC REDIRECTION VERS 'login.php'
echo "<h1>LES INFORMATIONS DE CONNEXION SONT REQUISES POUR SE CONNECTER A CETTE PAGE</h1>";
header("Location: login.php");   

}
?>

