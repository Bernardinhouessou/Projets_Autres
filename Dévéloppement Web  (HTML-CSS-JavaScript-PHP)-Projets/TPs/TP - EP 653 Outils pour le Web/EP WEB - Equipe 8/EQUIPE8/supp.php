<?php

//RECUPERATION DE LA VARIABLE 'suppr1' DE LA PAGE 'email.php' DANS $u2 A LA PAGE ACTUELLE 'supp.php' 
$su2=$_GET['suppr1'];

//REDIRECTION+PASSATION DE VARIABLE '$c' DANS 'suppr1' VERS 'gcatalogue.php'
header("Location: gcatalogue.php");

echo $su2;//juste un test pour debug

	//CONNECTION A LA BASE DE DONNEE 
    $con=mysqli_connect("localhost","root","","dbequipe8");  
	// VERIFICATION DE LA CONNEXION
	if (mysqli_connect_errno())
	{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	//REQUETE DE SUPPRESSION DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
    $result = mysqli_query($con,"DELETE  FROM Catalogue  where  imagepath='$su2'");
    
	//RÉCUPÈRE UNE LIGNE DE RÉSULTAT DANS UN TABLEAU ASSOCIATIF	
	$row = mysqli_fetch_array($result); 
	
	//EFFACER LE FICHIER $U2
    unlink($su2);
	//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE MYSQL
    mysqli_close($con);

?>		
