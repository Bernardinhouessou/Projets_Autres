<?php

//RECUPERATION DE LA VARIABLE 'n' DE LA PAGE 'gcatalogue' DANS $num A LA PAGE ACTUELLE 'selection.php'
$num=$_GET['n'];

	//CONNECTION A LA BASE DE DONNEE 
    $con=mysqli_connect("localhost","root","","dbequipe8");
	// VERIFICATION DE LA CONNEXION
	if (mysqli_connect_errno())
	{
	echo "Failed to connect to MySQL: " . mysqli_connect_error();
	}
	//REQUETE DE SELECTION SPECIFIQUE DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
    $result = mysqli_query($con,"SELECT * FROM Catalogue WHERE ID=$num");
			
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
header("Location:email.php?sup1=$direct");

//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE MYSQL
mysqli_close($con);

?>