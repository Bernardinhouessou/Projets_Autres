function fvalidate()
{
<!--DECLARATION DES VARIABLES-->
var marque = document.getElementById("marque").value;
var designa = document.getElementById("designation").value;ss
var faproduit =document.getElementById("faproduit").value;
var dimmension= document.getElementById("dimmension").value;
var poids= document.getElementById("poids").value;
var infotech= document.getElementById("infotech").value;
var prix= document.getElementById("prix").value;
var filevalue=document.getElementById("image").value;
<!--GESTION DE LA VALIDATION -->
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
