<html>
<head>
<meta charset="utf-8" />
<title></title>
</head>
<body>


<?php
//CONNECTION A LA BASE DE DONNEE  
$con=mysqli_connect("localhost","root","","dbequipe8");

// VERIFICATION DE LA CONNEXION
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
/*
$search1 = $_GET['ordB'];
$search2 = $_GET['ordP'];
$search3 = $_GET['ordTV'];
$search4 = $_GET['ordAP'];
$search5 = $_GET['ordTE'];
$search6 = $_GET['ordGPS'];
$search7 = $_GET['ordPC'];
$search8 = $_GET['ordXBOX'];
$search9 = $_GET['ordLOG'];
*/

//RECUPERATION DE LA VARIABLE PASSE
$searchX = $_GET['ordB'];





//REQUETE DE SELECTION DE VALEURS DANS LATABLE Catalogue de la  BASE DE DONNEES
$result = mysqli_query($con,"SELECT * FROM Catalogue WHERE Famille='$searchX' ");

		//DEBUT DU TABLEAU POUR L'AFFICHAGE DES DONEES
		echo "<table border='0' cellspacing='4' cellpadding='10' id='matable'><br>";
 				//CREATION DES ENTETES DU TABLEAU
		  			echo"<th>MARQUE</th><th>NOM</th><th>INFORMATIONS TECHNIQUES</th><th>PRIX</th><th>IMAGE</th>";
		  echo"<tr>";
		$flag1=0;
		 //RÉCUPÈRE UNE LIGNE DE RÉSULTAT DANS UN TABLEAU ASSOCIATIF	  
		 while($row = mysqli_fetch_array($result))
		{
			$numID=$row['ID'];
			$flag1=1;
		  echo"<td> " . $row['Marque'] . "</td>";
		  echo"<td> " . $row['Nom'] . "</td>";
		 // echo"<td> " . $row['Famille'] . "</td>";
		  echo"<td> " . $row['Infotech'] . "</td>";
	      echo"<td> " . $row['Prix']."<big style='scolor:green'> €</big></td>";  
		  echo '<td><img onmouseout="normalImg(this)" onmouseover="bigImg(this)" src="' . $row['imagepath'] . '" alt="Image path Invalid"  witdh="10" height="80" /></td>';
//
$pid=$row['ID'];
		  
		  echo"</tr>"; 
		}
		
                
echo "</table>";//fin du tableau
			
			
			//VARAIABLE '$flag' POUR AFFICHER 1msg SI AUCUN PRODUIT TROUVE
			if($flag1==0)
			{ 
				echo"<br>PRODUIT NON TROUVE";
				//echo'<br><a href="./Accueil.php">Accueil</a>';

			}
			
//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE	MYSQL
mysqli_close($con);
    ?>
    


<form name='form1' action="recu.php" method="POST" >
	<input type="button" value="Ajouter au Panier" onClick="addtocart(<?=$numID?>)" />
<form  id="recu " name="recu" action=" panier.php" method ="POST"><input type="text" name="recu" style="display:none" /><input type="submit" name="Acheter ce Produit" value="Acheter ce Produit" /></form> 
</body>    
</html>