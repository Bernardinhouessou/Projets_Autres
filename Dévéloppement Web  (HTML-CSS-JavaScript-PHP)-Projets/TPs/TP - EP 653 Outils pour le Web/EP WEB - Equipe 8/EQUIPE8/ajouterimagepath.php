
<?php
//header("Location: upload_image.php?page1=$_GET[$n]&page=$_POST[designation]");		
$con=mysqli_connect("localhost","root","","dbequipe8");
	
		// RETOURNE LE CODE D'ERREUR DE LA CONNEXION MYSQL 
		if (mysqli_connect_errno())
		  {
		  // RETOURNE LE MESSAGE D'ERREUR DE LA CONNEXION MYSQL 	  
		  echo "Failed to connect to MySQL: " . mysqli_connect_error();
	
		  }
		  
		//REQUETE DE SELECTION SPECIFIQUE DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
		$result = mysqli_query($con,"SELECT * FROM Catalogue WHERE Nom='$_GET[page]'");
	
	echo "<table border='1' cellspacing='4' cellpadding='10'>";
			
			echo"<tr>";
				while($row = mysqli_fetch_array($result))
				{
				
				echo"<td>" . $row['ID'] . "</td>";
				$n=$row['ID']; 
			echo"</tr>";
				
				}
	   echo"<br>";//espacement
	
	echo "</table>";//FIN DU TABLEAU

//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE MYSQL
mysqli_close($con);
    ?>