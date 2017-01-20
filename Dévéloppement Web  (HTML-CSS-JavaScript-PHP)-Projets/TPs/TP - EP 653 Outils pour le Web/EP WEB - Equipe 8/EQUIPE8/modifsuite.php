<!--Debut du code php-->
<?php
//POUR EMPECHER L'OUVERTURE DE CETTE PAGE 'modif.php' si l'utilisateur n'est PAS l'ADMINISTRATEUR 
         include('login3.php');
?>
<?php
//RECUPERATION DE LA VARIABLE 'n' DE LA PAGE 'gcatalogue.php' DANS '$num' A LA PAGE ACTUELLE 'modif.php' 
$num=$_GET['n'];

//CONNECTION A LA BASE DE DONNEE 
$con=mysqli_connect("localhost","root","","dbequipe8");
    // RETOURNE LE CODE D'ERREUR DE LA CONNEXION MYSQL
		if (mysqli_connect_errno())
		{
		// RETOURNE LE MESSAGE D'ERREUR DE LA CONNEXION MYSQL	  
		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		
		//REQUETE DE SELECTION SPECIFIQUE DE VALEURS DANS LA TABLE Catalogue de LA BASE DE DONNEES
		$result = mysqli_query($con,"SELECT  * FROM Catalogue  where  ID=$num");
 		
		//RÉCUPÈRE UNE LIGNE DE RÉSULTAT DANS UN TABLEAU ASSOCIATIF	
		$row = mysqli_fetch_array($result);
		//FERMETURE DE LA CONNEXION A LA BASE DE DONNEE MYSQL  
   		 mysqli_close($con);
?>
<!--Fin du code php-->

<!--Debut du code htmls-->
<!DOCTYPE html>
<head>
<link rel="stylesheet" href="./style/gestion.css"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>A-Ware:Gestion du Catalogue-Modifier Article</title>

<!--SCRIPT DE VALIDATION DU FORMULAIRE-->
<script type="text/javascript">
function fvalidate()
{
<!--DECLARATION DES VARIABLES-->
var marque = document.getElementById("marque").value;
var designa = document.getElementById("designation").value;
var faproduit =document.getElementById("faproduit").value;
var dimmension= document.getElementById("dimmension").value;
var poids= document.getElementById("poids").value;
var infotech= document.getElementById("infotech").value;
var prix= document.getElementById("prix").value;


<!--GESTION DE LA VALIDATION  DES CHAMPS VIDES DU FORMULAIRE-->
if(marque=="" || marque.length<1) 
{  
alert("La marque du produit ne doit pas etre vide ");  
document.getElementById("marque").focus();
return false;  
}
if(designa=="" || designa.length<1) 
{  
alert("La designation du produit ne doit pas etre vide ");  
document.getElementById("designation").focus();  
return false;  
}  
if(faproduit=="" || faproduit.length<1) 
{  
alert("Veuillez entrez la famille du produit ");  
document.getElementById("faproduit").focus();  
return false;  
}

if(dimmension=="" || dimmension.length<1) 
{  
alert("Veuillez entrez la dimmension du produit");  
document.getElementById("dimmension").focus();  
return false;  
}

if(poids=="" || poids.length<1) 
{  
alert("Veuillez entrez le poids du produit");  
document.getElementById("poids").focus();  
return false;  
}
if(infotech=="" || infotech.length<1) 
{  
alert("Veuillez entrez des informations techniques relatifs au produit ");  
document.getElementById("infotech").focus();  
return false;  
}
if(prix=="" || prix.length<1) 
{  
alert("Le prix du produit ne doit pas etre vide ");  
document.getElementById("prix").focus();  
return false;  
}

 
return true; 
}

</script> 
<!--Fin 1st script-->

<!--SCRIPT DE REDIMMESION D'IMAGE DU FORMULAIRE MODIF EN FONCTION DU PASSAGE DE LA SOURIS AU DESSUS-->
<script>
function bigImg(x)
{
x.style.height="200px";
x.style.width="200px";
}

function normalImg(x)
{
x.style.height="125px";
x.style.width="100px";
}
</script>
<!--FIN DU SCRIPT DE REDIMMESION D'IMAGE -->

</head>
<body>
<h1 align="center"><b>
<!--RECUPERATION ET AFFICHAGE DE LA VARIABLE n qui est le ID du prodiut -->
<p>MODIFIER L'IMAGE DE L' ARTICLE No 
<?php  echo $_GET['n']   ?></p></b></h1>

<!--DEBUT DU FORMULAIRE-->
<form action="upload_imagemodifsuite.php?s=<?php  echo $_GET['n']  ; ?>&e=<?php  echo $row['imagepath'] ;?> " method="post" enctype="multipart/form-data" onSubmit="return fvalidate()">
  <p>
  <!--<label for="id">ID: </label><input type="number" name="number" id="number" /><span id="errorid">* Entrer des nombres (0-9)</span><br />-->
  
   ANCIENNE IMAGE DU PRODUIT
  <br><img onmouseover="bigImg(this)" onmouseout="normalImg(this)"src="<?php  echo $row['imagepath'] ;?> " alt="Image Introuvalbe"  witdh="10" height="100" /> <br><br> REPERTOIRE DE L'IMAGE: "<?php  echo $row['imagepath'] ;?> "<br>
 
    
  <!--PASSATION DE  VARIABLE ARRAY  DU FORMULAIRE DE LA PAGEAJOUTERPHP A UNE  AUTRE PAGE -->
<form action="upload_image.php" method="post" enctype="multipart/form-data" onSubmit="return fvalidate()">
  <br />

<label for="image">Image (JPG, PNG ou GIF):</label><input type="file" name="image" id="image" /><br />
<input type="submit" name="ajouter" value="CONTINUER" />
 
  <p>
  <input type="text" name="n" value="' . $num . '" style="display:none" hidden="hidden"/>
  </p>
</form>
<!----------------fin du la formulaire-->


<!--SCRIPT DE VALIDATION NUMBERS & CHARACTERS DU FORMULAIRE-->
<script type="text/javascript" src="style/check.js"></script>
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

</body>
</html>