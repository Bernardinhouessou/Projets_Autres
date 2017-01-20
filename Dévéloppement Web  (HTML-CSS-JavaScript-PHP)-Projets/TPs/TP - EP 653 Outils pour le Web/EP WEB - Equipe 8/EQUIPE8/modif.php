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
<p>MODIFICATION DE L'ARTICLE  No <?php  echo $_GET['n']   ?></p></b></h1>

<!--DEBUT DU FORMULAIRE-->
<form action="upload_imagemodif.php?s=<?php  echo $_GET['n']  ; ?>&e=<?php  echo $row['imagepath'] ;?> " method="post" enctype="multipart/form-data" onSubmit="return fvalidate()">
<!--<label for="id">ID: </label><input type="number" name="number" id="number" /><span id="errorid">* Entrer des nombres (0-9)</span><br />-->
<label for="marque">Marque: </label><input type="text" name="marque" id="marque" autofocus  onkeypress="return validateLetter(this,'errormarque');" ondrop="return false;" onpaste="return false;"  value=<?php  echo $row['Marque']; ?> ><span id="errormarque">* Entrer des characteres (a-z)</span><br />
<label for="designation">Désignation du produit: </label><input type="text" name="designation" id="designation" onkeypress="return validateLetterNumber(this,'errordesignation');" ondrop="return false;" onpaste="return false;"  value=<?php  echo $row['Nom'] ;?> /><span id="errordesignation">* Entrer des characteres (a-z)</span><br />
<label for="faproduit">Famille du produit:</label>

<select name="faproduit" id="faproduit">
  <option value="Ordinateur de Bureau">Ordinateur de Bureau</option>
  <option value="Ordinateur Portable">Ordinateur Portable</option>
  <option value="TV et Ecran">TV et Ecran</option>
  <option value="Appareil Photo">Appareil Photo</option>
  <option value="Telephonie">Telephonie</option>
  <option value="GPS">GPS</option>
  <option value="PC">PC</option>
  <option value="Playstation 4">Playstation 4</option>
  <option value="XBOX One">XBOX One</option>
  <option value="Logiciels">Logiciels</option>
</select>

<!--<input type="t	ext" name="faproduit" id="faproduit" onkeypress="return validateLetter(this,'errorfaproduit');" ondrop="return false;" onpaste="return false;"  value=<?php  echo $row['Famille'] ;?> />-->

<span id="errorfaproduit" >* Entrer des characteres (a-z)</span><br />
<label for="dimmension">Dimmension:</label><input type="text" name="dimmension" id="dimmension"  onkeypress="return validateNumber(this,'errordim');" ondrop="return false;" onpaste="return false;" value=<?php  echo $row['Dimension'] ;?> /><span id="errordim">* Entrer des nombres (0-9)</span><br />

<label for="poids">Poids:</label><input type="text" name="poids" id="poids" onkeypress="return validateNumber(this,'errorpoids');" ondrop="return false;" onpaste="return false;"  value=<?php  echo $row['Poids'] ;?> /><span id="errorpoids">*Entrer des nombres (0-9)</span><br />
<label for="infotech">Informations techniques:</label><textarea id="infotech" name="infotech" id="infotech" /><?php  echo $row['Infotech'] ;?></textarea><span id="errorinfotech"></span><br />
<label for="prix">Prix:</label><input type="text" name="prix" id="prix"  onkeypress="return validateNumber(this,'errorprix');" ondrop="return false;" onpaste="return false;"  value=<?php  echo $row['Prix'] ;?>> <span id="errorprix">* Entrer des nombres (0-9)</span><br />

<br />



<label for="image">IMAGE DU PRODUIT <br>
<br>

<!--//RECUPERATION DE VARIABLE 'n' dans '$num'  ===>$num=$_GET['n'] DANS encore 'n' 

-->
<b id='LinkModifIMage'><a href="./modifsuite.php?w=&n='<?php  echo $num ?>'" target="_self">CLIQUER ICI POUR MODIFIER L'IMAGE</a></b>


<br>
<img onmouseover="bigImg(this)" onmouseout="normalImg(this)"src="<?php  echo $row['imagepath'] ;?> " alt="Image Introuvalbe"  witdh="10" height="100" /><br> REPERTOIRE DE L'IMAGE: "<?php  echo $row['imagepath'] ;?> "<br><br />
<input type="text" name="n" value="' . $num . '" style="display:none" hidden="hidden"/>
<input type="submit" name="modifier" value="CONTINUER"/>
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