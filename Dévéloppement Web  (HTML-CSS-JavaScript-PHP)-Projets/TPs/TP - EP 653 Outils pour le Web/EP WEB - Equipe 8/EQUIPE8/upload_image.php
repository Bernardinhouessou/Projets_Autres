<?php
//FAIT APPELL A CETTE PAGE POUR LA CREATION AUTOMATIQUE DE LA BASE DE DONNEE ET DES TABLES 
//include('Create DB&Tables.php');
//VERIFICATION DU TYPE DE FICHIER  DEBUT 
if (( ($_FILES['image']['type'] == 'image/gif')
       || ($_FILES['image']['type'] == 'image/jpeg')
       || ($_FILES['image']['type'] == 'image/png') )
&& ($_FILES['image']['size'] < 900000))
{
    //VERIFICATION D'ERREUR DURANT L'ENVOIE (OU TELECHARGEMENT)DU FICHIER OU IMAGE 
    if ($_FILES['image']['error'] == 0)
    {
        echo 'Nom du Fichier Envoye:  ' . $_FILES['image']['name'] . '<br />';//upload file name
        echo 'Type de Fichier:         ' . $_FILES['image']['type'] . '<br />';//File Type
        echo 'Taille du Fichier:         ' . ($_FILES['image']['size'] / 1024) . ' Kb<br />';//File Type
        
		//RECUPERATION DE LA BASE OU DESIGNATION DE L'IMAGE A PARTIR DE l'URL EN EVITANT L"EXTENSION
        $fileData = pathinfo($_FILES['image']['name']);//RETOURNE DES INFORMATIONS SUR LE CHEMIN DE L'IMAGE
        $fileExtension = $fileData['extension'];//RECUPERATION DE L'EXTENSION DE L'IMAGE
        $fileName = $_POST['designation'] . '.' . $fileExtension;//CREATION DU NOUVEAU NOM

        $filePath = 'images/' . $fileName;//CREATION DU REPEROIRE A PARTIR DU NOUVEAU NOM
		
        if (!file_exists($filePath))//VERIFICATION DE L'EXISTANCE DE L'IMAGE
        {
			//DÉPLACEMENT DE L'IMAGE TÉLÉCHARGÉ VERS UN NOUVEL EMPLACEMENT
            move_uploaded_file($_FILES['image']['tmp_name'], $filePath);
            echo 'Sauvegarde sous: ' . $filePath . '<br />';
            echo 'Nom du Fichier Envoyé:<br>';
            echo '<img src="' . $filePath . '" width="100" height="100" alt="Chemin invalide" >';

//CONNECTION A LA BASE DE DONNEE  
            $con=mysqli_connect("localhost","root","","dbequipe8");
            // VERIFICATION DE LA CONNEXION
            if (!mysqli_connect_errno())//RETOURNE LE CODE D'ERREUR DE LA CONNEXION MYSQL 
            {
				//REQUETE D'INSERTION(AJOUT) DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
                $sql="INSERT INTO Catalogue (Marque,Nom,Famille,Dimension,Poids,Infotech,Prix,imagepath)
                VALUES
                ('$_POST[marque]','$_POST[designation]','$_POST[faproduit]','$_POST[dimmension]','$_POST[poids]','$_POST[infotech]','$_POST[prix]','$filePath')";

				//SI CONNEXION BONNE ET REQUETE BONNE 
                if (mysqli_query($con,$sql))
                {
                    echo 'UN NOUVEAU PRODUIT VIENT D\'ETRE AJOUTE' . '<br>';
					
					//REDIRECTION VERS PAGE//peut ETRE COMMENTER POUR DETECTION D'ERREUR
					header("location:gcatalogue.php");
                    mysqli_close($con);
                }
				//SI CONNEXION PAS BONNE ET REQUETE PAS BONNE
                else
                {
                    echo 'Error: ' . mysqli_error($con);
                }
            }
            else
            {	//RETOURNE LE CODE D'ERREUR DE LA CONNEXION MYSQL 
                echo 'ECHEC DE CONNEXION A LA BASE DE DONNEE MYSQL: ' . mysqli_connect_error();
            }
        }
        else
        {	//RECUPERATION DE LA VARIABLE ENTRE DS LE CHAMP DESIGNTION PRODUIT 
			$amodif=$_POST['designation'];
			echo "<br>";
			echo '<b>FICHIER IMAGE DEJA EXISTANTE</b><br>';
			echo "<br>VEUILLEZ CHANGER LE TITRE OU LA DESIGNATION:<b> ".$amodif."</b> PAR UN(E) AUTRE EN CLIQUANT SUR LE BOUTON CI-DESSOUS";
			 echo "<br><br>";
			
		echo'<input type="submit" value="ALLER MODIFIER LA DESIGNATION DU PRODUIT "<a href="#" onclick="javascript:history.go(-1) ";"></a>';

			echo"<br><br>";
						
//print_r ($_POST);//juste un test pour voir les donees passes que j'essayais
//$e=print_r ($_POST);//juste un test pour voir les donees passes que j'essayais
			//header("Location: ajouter.php?redu=$_POST()");//juste un test que j'essayais
			
        }
    }
    else
    { //MESSAGE D'ERREUR
        echo 'ERREUR SUR LE FICHIER:  ' . $_FILES['image']['error'] . '<br />';
    }
}
else
{
    ////VERIFICATION DU TYPE DE FICHIER  DEBUT(suite)
    echo 'DETAILS DU FICHIER INVALIDE  ::<br> Type de Fichier ::' . $_FILES['image']['type'] . ', Taille du Fichier::: ' . $_FILES['image']['size'];
}
?>
