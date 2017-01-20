<?php

echo "<p id=ERRORBASE>";
?>
<?php

/*
---------------------------------------------------------------------------------------------------------------
TO USE THIS JUST CHANGE :

the $database='the name of your databse' three times in the following codes ;
As we are Equipe 8 We will have 'dbequipe8' as name of our database

$hostname='localhost';

 
---------------------------------------------------------------------------------------------------------------
*/
//CONNECTION A LA BASE DE DONNEE 
//variable de creation de base de donnee 
$hostname='localhost';
$username='root';
$password='';
$database='dbequipe8';
$con=mysqli_connect($hostname,$username,$password);
//VERIFICATION DE LA CONNEXION AVEC LA BASE DE DONNEES
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

// CREATION DE LA BASE DE DONNEES 'dbequipe8' 
		$sq1="CREATE DATABASE $database";

//EXÉCUTE UNE REQUÊTE SUR LA BASE DE DONNÉES
if (mysqli_query($con,$sq1))
  {      
  //echo "<b>BASE DE DONNEES ' ".$database. " ' CREER AVEC SUCCESS<b>";
  }
else
  {      
  //echo "VEUILLEZ CREER D'ABORD  LA BASE DE DONNEES: " . mysqli_error($con);
  }

?>

<?php
echo"<br><br>";
?>

<?php
$database='dbequipe8';
$con1=mysqli_connect($hostname,$username,$password,$database);
//VERIFICATION DE LA CONNEXION AVEC LA BASE DE DONNEES
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

			//CREATION DES TABLEAUX 
$sq2="CREATE TABLE catalogue (
   ID int(4) unsigned NOT NULL AUTO_INCREMENT,
   PRIMARY KEY (ID),
   UNIQUE KEY  ID (ID),
   Marque varchar(20) NOT NULL,
   Nom varchar(20) NOT NULL,
   Famille varchar(20) NOT NULL,
   Dimension float NOT NULL,
   Poids float NOT NULL,
   Infotech varchar(100) NOT NULL,
   Prix int(10) NOT NULL,
   imagepath varchar(100) NOT NULL)";

//EXÉCUTE UNE REQUÊTE SUR LA BASE DE DONNÉES
if (mysqli_query($con1,$sq2))
  {      
  
 // echo "<b>TABLEAU Catalogue CREER AVEC SUCCESS<b>";
  }
else
  {
  //echo "VEUILLEZ CREER  D'ABORD DU TABLEAU Catalogue: " . mysqli_error($con1);
  }
   
?>


<?php
echo"<br><br>";
?>

<?php
$database='dbequipe8';
$con1=mysqli_connect($hostname,$username,$password,$database);
//VERIFICATION DE LA CONNEXION AVEC LA BASE DE DONNEES
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

			//CREATION DES TABLEAUX 
$sq3="CREATE TABLE users (
   IDuser int(4) unsigned NOT NULL AUTO_INCREMENT,
   username varchar(20) NOT NULL,
   PRIMARY KEY (IDuser),
   UNIQUE KEY  ID (IDuser),
   password varchar(20) NOT NULL)";

//EXÉCUTE UNE REQUÊTE SUR LA BASE DE DONNÉES
if (mysqli_query($con1,$sq3))
  {      
  
//  echo "<b>TABLEAU users CREER AVEC SUCCESS<b>";
  }
else
  {
 // echo "VEUILLEZ CREER  D'ABORD LA CREATION DU TABLEAU users: " . mysqli_error($con1);
  }
echo"<br><br>";
?>


<?php
$database='dbequipe8';
$con1=mysqli_connect($hostname,$username,$password,$database);
//VERIFICATION DE LA CONNEXION AVEC LA BASE DE DONNEES
if (mysqli_connect_errno())
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }

			//CREATION DES INFOS DE LADMINISTRATEUR PAR DEFAUT  
					//ON PEUT UTILISER md5()pour encrypter le mot de passe
                $sq4="INSERT INTO users (IDuser,username,password)
                VALUES
                ('1','admin','admin')";
//EXÉCUTE UNE REQUÊTE SUR LA BASE DE DONNÉES
if (mysqli_query($con1,$sq4))
  {      
  
 // echo "<b>CREATION DES IDENTIFIANTS ADMINISTRATEUR DANS LE TABLEAU users AVEC SUCCESS<b>";
  }
else
  {
  //echo "VEUILLEZ CREER  D'ABORD UN IDENTIFIANT ADMINISTRATEUR PAR DEFAUT DANS LE TABLEAU users : " . 
  mysqli_error($con1);
  }

  // echo'<br><br><input type="submit" value="OK" id="OK"<a href="#" onclick="action();"></a>';
echo"<br><br>";
?>

<!-- SCRIPT POUR FAIRE DISPARAITRE LES BOUTTONS DE id='OK' DE CETTE PAGE-->
<script>
    var hidden = false;
    function action() {
        hidden = !hidden;
        if(hidden) {
            document.getElementById('OK').style.visibility = 'hidden';
			document.getElementById('ERRORBASE').style.visibility = 'hidden';
			
        } else {
            document.getElementById('OK').style.visibility = 'visible';
			document.getElementById('ERRORBASE').style.visibility = 'visible';
        }
    }
</script>


<?php
echo "</p>";

?>