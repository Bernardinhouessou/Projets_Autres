<?php

//RECUPERATION DE LA VARIABLE 's' DE LA PAGE 'gcatalogue' DANS $num1 A LA PAGE ACTUELLE 'upload_imagemodif.php'
$num1=$_GET['s'];

//RECUPERATION DE LA VARIABLE 'e' DE LA PAGE 'gcatalogue' DANS $imga A LA PAGE ACTUELLE 'upload_imagemodif.php'
$imga=$_GET['e'];

			//CONNECTION A LA BASE DE DONNEE  
			$con=mysqli_connect("localhost","root","","dbequipe8");
			
			// VERIFICATION DE LA CONNEXION
			if (mysqli_connect_errno())//RETOURNE LE CODE D'ERREUR DE LA CONNEXION MYSQL 
			{
			echo "Failed to connect to MySQL: " . mysqli_connect_error();//RETOURNE UN MESSAGE D'ERREUR DE LA CONNEXION MYSQL 
			}

//FAIT APPELL A CETTE PAGE POUR LA CREATION AUTOMATIQUE DE LA BASE DE DONNEE ET DES TABLES 
//include('Create DB&Tables.php');
//VERIFICATION DU TYPE DE FICHIER  DEBUT 
if (( ($_FILES['image']['type'] == 'image/gif')
       || ($_FILES['image']['type'] == 'image/jpeg')
       || ($_FILES['image']['type'] == 'image/png') )
&& ($_FILES['image']['size'] < 900000))
{
		  
		  if (file_exists("images/".$_FILES["image"]["name"]))
			{
			   echo "<b>".$_FILES["image"]["name"] . " already exists. </b>";
			}else
			{
			   move_uploaded_file($_FILES["image"]["tmp_name"],"images/". $_FILES["image"]["name"]);
			   echo "Stored in: " . "images/" . $_FILES["image"]["name"]."<br />";
			   
			}
			
		//	$fileData = pathinfo($_FILES['image']['name']);//RETOURNE DES INFORMATIONS SUR LE CHEMIN DE L'IMAGE
        //	$fileExtension = $fileData['extension'];//RECUPERATION DE L'EXTENSION DE L'IMAGE
			$newimage = 'images/' . $_FILES["image"]["name"];
			 
			 
//REQUETE DE MISE A JOUR SPECIFIQUE DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
$sql="UPDATE  Catalogue  SET imagepath='$newimage'   WHERE  ID=$num1";

/*  
  $target = $new12;       //CECI EST LE FICHIER QUI EXISTE ACTUELLEMENT OU LA CIBLE DU LIEN.
  $link = $imga;   		 //CECI SERA LE NOM DU FICHIER QUE VOUS VOULEZ LIER OU LE NOM DU LIEN
  link($target, $link);	// CRÉER LE LIEN 
  */
 
//  unset($);


// VERIFICATION DE LA CONNEXION
if (!mysqli_query($con,$sql))
  {
  // Alias de la fonction exit .......	  
  die('Error: ' . mysqli_error($con));
  }

  
//REDIRECTION+PASSATION DE VARIABLE '$imga' DANS 'w' VERS 'gcatalogue.php' 
//header("Location:gcatalogue.php?w=$imga");
header("Location:gcatalogue.php");

echo "MODIFICATION EFFECTUER AVEC SUCCES"."<br>";

//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE MYSQL
mysqli_close($con);
}
?>
