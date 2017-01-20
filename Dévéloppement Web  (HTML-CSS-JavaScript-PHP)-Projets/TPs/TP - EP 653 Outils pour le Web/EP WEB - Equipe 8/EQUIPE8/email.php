<?php
//DE LA VARIABLE 'sup1' DE LA PAGE 'selection.php' DANS $supp2 A LA PAGE 'email.php'

$supp2=$_GET['sup1'];

//echo $supp2;//juste un test de debug

//REDIRECTION+PASSATION DE VARIABLE '$c' DANS 'suppr1' VERS
header("Location: supp.php?suppr1=$supp2");
?>