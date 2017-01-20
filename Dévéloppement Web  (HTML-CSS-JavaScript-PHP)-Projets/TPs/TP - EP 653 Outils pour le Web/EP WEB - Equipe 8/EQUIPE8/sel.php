<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Untitled Document</title>
</head>

<body>
<?php

//RECUPERATION DE LA VARIABLE 'n' DE LA PAGE 'gcatalogue' DANS $num A LA PAGE ACTUELLE 'selection.php'
$num=$_GET['productid'];
$com=$_GET['command'];
$pri=$_GET['price'];
$name=$_GET['nproduit'];


	//CONNECTION A LA BASE DE DONNEE 
    $con=mysqli_connect("localhost","root","","dbequipe8");
	// VERIFICATION DE LA CONNEXION
	if (mysqli_connect_errno())
	{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	//REQUETE DE SELECTION SPECIFIQUE DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
   
   $sql="INSERT INTO Commande (PID,Nom,quantite,prix,command)
                VALUES
                ('$num','$_POST[designation]','$_POST[faproduit]','$_POST[dimmension]','$_POST[poids]',)";
   
   
   
    $result = mysqli_query($con,"SELECT * FROM command WHERE ID=$num");
			
		  //CREATION DE TABLEAU	
 		  echo "<table border='1' cellspacing='4' cellpadding='10' id='matable'>";
		  echo"<tr><th>ID</th><th>Repertoire de l'image</th><tr>";

		  //RÉCUPÈRE UNE LIGNE DE RÉSULTAT DANS UN TABLEAU ASSOCIATIF	
 		  while($row = mysqli_fetch_array($result))
          {
			//CREATION DE VARIABLES
 			$n=$row['ID'];
 			$direct=$row['imagepath'];

			echo"<td>".$row['ID']."</td>";			
			echo"<td>".$row['imagepath']."</td></tr>";
          }
echo "</table>";//fin tableau

//REDIRECTION+PASSATION DE VARIABLE '$direct' DANS 'sup1' VERS 'email.php' 
header("Location:panier.php?");

//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE MYSQL
mysqli_close($con);

?>
</body>
</html>