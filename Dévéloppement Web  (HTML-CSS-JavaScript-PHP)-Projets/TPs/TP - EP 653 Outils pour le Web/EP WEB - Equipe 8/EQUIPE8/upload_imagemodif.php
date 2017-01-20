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

		$fileData = pathinfo(basename($imga));//RETOURNE DES INFORMATIONS SUR LE CHEMIN DE L'IMAGE
		$fileExtension = $fileData['extension'];//RECUPERATION DE L'EXTENSION DE L'IMAGE
		$new=$_POST['designation'].".".$fileExtension;//CREATION DU NOUVEAU NOM
		$new12="./images/$new";//CREATION DU REPEROIRE A PARTIR DU NOUVEAU NOM

//RENOMMER '$imga' EN '$new12' EN LE DÉPLAÇANT DE RÉPERTOIRE SI NÉCESSAIRE. SI '$new12' EXISTE, IL SERA ÉCRASÉ.
  rename($imga, $new12); 


//REQUETE DE MISE A JOUR SPECIFIQUE DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
$sql="UPDATE  Catalogue  SET Marque='$_POST[marque]',Nom='$_POST[designation]',Famille='$_POST[faproduit]',Dimension='$_POST[dimmension]',Poids='$_POST[poids]',Infotech='$_POST[infotech]',Prix='$_POST[prix]',imagepath='$new12'   WHERE  ID=$num1";

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
header("Location:gcatalogue.php?w=$new12");


echo "MODIFICATION EFFECTUER AVEC SUCCES"."<br>";

//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE MYSQL
mysqli_close($con);

?>