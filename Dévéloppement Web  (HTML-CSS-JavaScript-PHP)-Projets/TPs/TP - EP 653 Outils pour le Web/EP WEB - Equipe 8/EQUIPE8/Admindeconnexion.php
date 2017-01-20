<?php
//DETRUIRE LA SESSION ET REDIRECTION VERS 'login.php'
session_start();
session_destroy();
header('location:login.php');
?>