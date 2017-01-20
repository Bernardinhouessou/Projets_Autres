<?php  

//UTILISATION DE PHPHMAILER POUR L'ENVOIE DU MAIL 
require("class.phpmailer.php"); // path to the PHPMailer class


//CONNEXION A UN COMPTE GMAIL EXISTANT POUR ENVOYER LE MAIL DE LA BAS
$mail = new PHPMailer();   
$mail->IsSMTP();  // telling the class to use SMTP
$mail->Mailer = "smtp";
$mail->Host = "ssl://smtp.gmail.com";
$mail->Port = 465;
$mail->SMTPAuth = true; // turn on SMTP authentication
//ICI NOUS AVONS UN COMPTE GMAIL CREE AU HASARD POUR ENVOYER LES MAILS NE SURTOUT PAS METTRE son vrai mail et mot de passe a cette partie la
$mail->Username = "saisissezvotreadresseelectronique@gmail.com"; // SMTP username
$mail->Password = "saisisservotremotdepasse"; // SMTP password 

 
 
 
//$mail->From     = ($_POST['FROM']);
$mail->AddAddress($_POST['TO']);   
$mail->Subject  = $_POST['subject'];
$mail->Body     = 'MESSAGE DE :'.$_POST['FROM']."                                     												                             ".'ENVOYE AVEC SON ADRESSE MAIL: '.$_POST['ymail'].'	                             '.'                                                                                                       '.'SON MESSGE EST: '.$_POST['detail'];
$mail->WordWrap = 50;   
if(!$mail->Send()) {
echo 'Message was not sent.';
echo 'Mailer error: ' . $mail->ErrorInfo;
} else {
echo 'Message has been sent.';
}
?>