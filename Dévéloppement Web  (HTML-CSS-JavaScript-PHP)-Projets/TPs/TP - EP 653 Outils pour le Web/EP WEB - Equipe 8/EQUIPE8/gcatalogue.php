<!DOCTYPE html>
<head>
<link rel="stylesheet" href="./style/gestioncata.css"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>A-Ware:Gestion du Catalogue-Liste des articles</title>


<?php
//UTLISER UNE FOIS LE FICHIER login3.php pour PRESENTER LES INFORMATIONS DE CONNEXIONS ADMINISTATEURS ET LE NOMBRE DE FOIS CCETTE PAGE A ETE VISITE
include('login3.php');
//echo date('D M Y H:i:s');//JUSTE UN TEST
?>

<!--SCRIPT DE REDIMMESION D'IMAGE DU FORMULAIRE CATALOGUE EN FONCTION DU PASSAGE DE LA SOURIS AU DESSUS-->
<script>
function bigImg(x)
{
x.style.height="200px";
x.style.width="200px";
}

function normalImg(x)
{
x.style.height="80px";
x.style.width="100px";
}
</script>
</head>
<body>
<b id=titre>LISTE DES PRODUITS DE LA BASE DE DONNEES</b><br>
<p id=ERRORBASE><input type="submit" name="AJOUTER" onClick="document.location.href='./ajouter.php'" value="AJOUTER UN NOUVEAU PRODUIT" id=AJOUTERCATA />
</p>

<!--DEBUT PHP CODE-->

<?php

//include('Create DB&Tables.php');juste un test
//CONNECTION A LA BASE DE DONNEE  
$con=mysqli_connect("localhost","root","","dbequipe8");

// VERIFICATION DE LA CONNEXION
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

//REQUETE DE SELECTION DE VALEURS DANS LATABLE Catalogue de la  BASE DE DONNEES
$result = mysqli_query($con,"SELECT * FROM Catalogue");

		//DEBUT DU TABLEAU POUR L'AFFICHAGE DES DONEES
		echo "<table border='1' cellspacing='4' cellpadding='10' id='matable'><br>";
 				//CREATION DES ENTETES DU TABLEAU
		  			echo"<th>ID</th><th>Marque</th><th>Nom</th><th>Famille</th><th>Dimension</th><th>Poids</th><th>Infotech</th><th>Prix</th><th>Repertoire de l'image</th><th>image</th><th colspan='2'>Operations</th>";
		  echo"<tr>";

		 //RÉCUPÈRE UNE LIGNE DE RÉSULTAT DANS UN TABLEAU ASSOCIATIF	  
		 while($row = mysqli_fetch_array($result))
		{
		  
		  echo"<td>" . $row['ID'] . "</td>";
		  echo"<td> " . $row['Marque'] . "</td>";
		  echo"<td> " . $row['Nom'] . "</td>";
		  echo"<td> " . $row['Famille'] . "</td>";
		  echo"<td> " . $row['Dimension'] . "</td>";
		  echo"<td> " . $row['Poids'] . "</td>";
		  echo"<td> " . $row['Infotech'] . "</td>";
	      echo"<td> " . $row['Prix'] . "</td>";
		  echo"<td> " . $row['imagepath'] . "</td>";
		  
		  //$n=$row['ID'];
		  //$direct=$row['imagepath'];

		
		
		  echo '<td><img onmouseout="normalImg(this)" onmouseover="bigImg(this)" src="' . $row['imagepath'] . '" alt="Image path Invalid"  witdh="10" height="80" /></td>';
		
		  echo'<td><form  id="modif " name="modif" action=" modif.php" method ="GET"><input type="text" name="n" value="'. $row['ID'].'" style="display:none" /><input type="text" name="repupdat" value="'. $row['imagepath'].'" style="display:none" /><input type="submit" name="modifier" value="Modifier" /></form> </td>';
		
		  echo'<td><form  id="supp " name="supp" action=" selection.php" method ="GET"><input type="text" name="n" value="'. $row['ID'].'" style="display:none" /><input type="submit" name="supp" value="Supprimer" /></form> </td>';
		  
		  echo"</tr>"; 
		}
echo "</table>";//fin du tableau
			
//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE	MYSQL
mysqli_close($con);
    ?>
<!--FIN PHP CODE-->

</body>
</html>
