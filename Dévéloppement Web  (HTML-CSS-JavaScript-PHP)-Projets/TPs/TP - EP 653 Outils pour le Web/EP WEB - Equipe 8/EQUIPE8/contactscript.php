<?php  

//UTILISATION DE PHPHMAILER POUR L'ENVOIE DU MAIL 
require("class.phpmailer.php"); // path to the PHPMailer class


//CONNEXION A UN COMPTE GMAIL EXISTANT POUR ENVOYER LE MAIL DE LA BAS
$mail = new PHPMailer(); 
// UTILISATION DU PROTOCOL SMTP PAR LA CALSSE PHPMAILER  
$mail->IsSMTP();  
$mail->Mailer = "smtp";
$mail->Host = "ssl://smtp.gmail.com";
$mail->Port = 465;
$mail->SMTPAuth = true; // turn on SMTP authentication
//ICI NOUS AVONS UN COMPTE GMAIL CREE AU HASARD POUR ENVOYER LES MAILS NE SURTOUT PAS METTRE son vrai mail et mot de passe a cette partie la
$mail->Username = "polytechequipe@gmail.com"; // SMTP username
$mail->Password = "polytechequipe789"; // SMTP password 

 
 
 
//$mail->From     = ($_POST['FROM']);
$mail->AddAddress('polytechequipe@gmail.com');   
$mail->Subject  = $_POST['Motif'];
$mail->Body     = 'NUMERO CLIENT :'.$_POST['numero'].'                          '." OBJET DE L'ENVOI:   ".$_POST['Motif'].'                    '.'EMAIL DU CLIENT: '.$_POST['ymail'].'	               '.'                                                             '.'SON MESSAGE EST: '.$_POST['message'];
$mail->WordWrap = 50;   
if(!$mail->Send()) {
echo 'Message was not sent.';
echo 'Mailer error: ' . $mail->ErrorInfo;
} else {
echo 'Message has been sent.';
}
?>