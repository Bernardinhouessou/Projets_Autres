<html>
<head>
<meta charset="utf-8" />
<title></title>
<script language="javascript">
	function addtocart(pid,pie){
		document.form1.productid.value=pid;
		document.form1.command.value='add';
		document.form1.submit();
	}
</script>
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
//variable
$search1=$_POST['search'];   //
$search2=$_POST['search12'];//

//REQUETE DE SELECTION DE VALEURS DANS LATABLE Catalogue de la  BASE DE DONNEES
$result = mysqli_query($con,"SELECT * FROM Catalogue WHERE Nom LIKE '%".$search1."%'"." AND Famille LIKE '%".$search2."%'");

		//DEBUT DU TABLEAU POUR L'AFFICHAGE DES DONEES
		echo "<table border='0' cellspacing='4' cellpadding='10' id='matable'><br>";
 				//CREATION DES ENTETES DU TABLEAU
		  			echo"<th>MARQUE</th><th>NOM</th><th>INFORMATIONS TECHNIQUES</th><th>PRIX</th><th>IMAGE</th>";
		  echo"<tr>";
		$flag1=0;
		 //RÉCUPÈRE UNE LIGNE DE RÉSULTAT DANS UN TABLEAU ASSOCIATIF	  
		 while($row = mysqli_fetch_array($result))
		{
			$flag1=1;
			$numID=$row['ID'];
			$nproduit=$row['Nom'];
			$price=$row['Prix'];

			
		  echo"<td> " . $row['Marque'] . "</td>";
		  echo"<td> " . $row['Nom'] . "</td>";
		 // echo"<td> " . $row['Famille'] . "</td>";
		  echo"<td> " . $row['Infotech'] . "</td>";
	      echo"<td> " . $row['Prix']."<big style='scolor:green'> €</big></td>";  
		  echo '<td><img onmouseout="normalImg(this)" onmouseover="bigImg(this)" src="' . $row['imagepath'] . '" alt="Image path Invalid"  witdh="10" height="80" /></td>';

		  
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

<form name='form1' action="panier.php"> 
<br><br>
<input type="hidden" name="productid" />
    <input type="hidden" name="command" /><br><br>
    <input type="button" value="Ajouter au Panier" onClick="addtocart(<?=$numID?>)" />
    
    
  <input type="button" value="Acheter ce Produit" onClick="recu.php" />
  </form>
</body>    
</html>