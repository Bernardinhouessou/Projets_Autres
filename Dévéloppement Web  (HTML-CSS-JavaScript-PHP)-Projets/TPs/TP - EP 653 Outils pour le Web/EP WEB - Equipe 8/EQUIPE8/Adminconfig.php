<?php
//REDIRECTION VERS
include('login3.php');
?>
<form action="#" method =post>
<fieldset>
<legend>MODIFIER SES INFORMATIONS DE CONNEXION</legend>
<table width=650>
<tr>
<td height="38">UTILISATEUR</td>
<td><input type=text name=username VALUE='<?php echo $_SESSION['Log']?>'></td>
</tr>
<tr>
<td height="29"><label for='mot de passe' id=mdplogin>MOT DE PASSE</label></td>
<td><input type=password name=password></td>
</tr>
<tr>
<td colspan=2><p>&nbsp;
  </p>
  <p>
    <input type='submit' name=submit id='submit' value="Changer">
  </p></td>
</tr>
</table>
</fieldset>
</form>

<?php 
//ACTION FAITE APRES AVOIR CLIQUER SUR LE BOUTON CHANGER
if(isset($_POST['username']))
	{
	$username = $_POST['username'];
	$password = $_POST['password'];

	//CONNEXION A LA BASE DE DONNEE ET UTILISATION D'UNE REQUETE DE SELECTION POUR LE TABLEAU 'users' 
	mysql_connect("localhost", "root", "");
	mysql_select_db("dbequipe8");
	$query="UPDATE  users  SET username='$username',password='$password' WHERE username='$username'";

	$result = mysql_query($query) 
	or die("PAS DE BASE DE DONNEES".mysql_error());
	$num_row = mysql_num_rows($result);
	//REDIRECTION VERS
	header("Location:login.php");
	

	//IDENTIFICATION ERRONEE(MOT DE PASSE.......ERRREUR)
	if($flag==0)
	{
	echo "<font color=red>VEUILLEZ ENTRER DES INFORMATIONS DE CONNEXIONS VALABLES...</font>";
	
	}

}

?>
