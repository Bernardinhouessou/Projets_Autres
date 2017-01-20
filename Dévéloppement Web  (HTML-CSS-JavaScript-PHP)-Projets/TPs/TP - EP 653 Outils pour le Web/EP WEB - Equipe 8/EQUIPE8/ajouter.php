<?php
         include('login3.php');
?>

<!DOCTYPE html>
<head>
<link rel="stylesheet" href="./style/gestion.css"  />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>A-Ware:Gestion du Catalogue-Ajouter Article</title>

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
var filevalue=document.getElementById("image").value;
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

if(filevalue=="" || filevalue.length<1)
{
alert("Choisissez au moins une image ");
document.getElementById("image").focus();
return false;
} 
return true; 
}

</script> 



</head>
<body>
<h1 align="center"><b><p>AJOUTER UN ARTICLE</p></b></h1>


<!--DEBUT DU FORMULAIRE-->
                <!-- POUR UN TEST DES VALEURS A PASSER UTILISER CECI
                <form action="upload_image.php?up1=<?php print_r ($_POST);?>" method="post" enctype="multipart/form-data" onSubmit="return fvalidate()">
                -->


		<!--PASSATION DE  VARIABLE ARRAY  DU FORMULAIRE DE LA PAGEAJOUTERPHP A UNE  AUTRE PAGE -->
<form action="upload_image.php" method="post" enctype="multipart/form-data" onSubmit="return fvalidate()">
<label for="marque">Marque: </label><input type="text" name="marque" id="marque" autofocus  onkeypress="return validateLetter(this,'errormarque');" ondrop="return false;" onpaste="return true;"  /><span id="errormarque">* Entrer des characteres (a-z)</span><br />
<label for="designation">Désignation du produit: </label><input type="text" name="designation" id="designation" onkeypress="return validateLetterNumber(this,'errordesignation');" ondrop="return false;" onpaste="return true;"  /><span id="errordesignation">* Entrer des characteres (a-z)</span><br />
<label for="faproduit">Famille du produit:</label> 
<!--
<input type="t	ext" name="faproduit" id="faproduit" onkeypress="return validateLetter(this,'errorfaproduit');" ondrop="return false;" onpaste="return true;"  />
-->

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

<span id="errorfaproduit" >* Entrer des characteres (a-z)</span><br />
<label for="dimmension">Dimmension:</label><input type="text" name="dimmension" id="dimmension"  onkeypress="return validateNumber(this,'errordim');" ondrop="return false;" onpaste="return true;" /><span id="errordim">* Entrer des nombres (0-9)</span><br />

<label for="poids">Poids:</label><input type="text" name="poids" id="poids" onkeypress="return validateNumber(this,'errorpoids');" ondrop="return false;" onpaste="return true;"  /><span id="errorpoids">*Entrer des nombres (0-9)</span><br />
<label for="infotech">Informations techniques:</label><textarea id="infotech" name="infotech" id="infotech" ></textarea><span id="errorinfotech"></span><br />
<label for="prix">Prix:</label><input type="text" name="prix" id="prix"  onkeypress="return validateNumber(this,'errorprix');" ondrop="return false;" onpaste="return true;" /><span id="errorprix">* Entrer des nombres (0-9)</span><br />

<label for="image">Image (JPG, PNG ou GIF):</label><input type="file" name="image" id="image" /><br />
<input type="submit" name="ajouter" value="Ajouter" />
<input type="reset"  name ="Effacer tout" value="Effacer tout" / > 
</form>



<!--SCRIPT DE VALIDATION DES NOMBRES & CHARACTERES DU FORMULAIRE-->
<script type="text/javascript" src="style/check.js"></script>
</body>
</html>
