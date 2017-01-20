
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="style.css" />
        <title>Jeux PC</title>
        
    </head>

    <body>
    <H1><span><a href="Acceuil.html">BigDeal.fr</a></span></H1>
    <div><input align="absbottom"type="text" id="twotabsearchtextbox" width="400" height="300" title="Search For" value="" name="field-keywords" autocomplete="off"></div>
    <H2>Jeux PC</H2>
    <table>
  	 	 <tr>
       		<td>
       			<div id="menu">
					
					<div class="menu" id="menu1" onclick="afficheMenu(this)">
						<a href="#">Informatique</a>
					</div>
					
					<div id="sousmenu1" style="display:none">
						<div class="sousmenu">
							<a href="PCBureau.html">Ordinateur de Bureau</a>
						</div>
						<div class="sousmenu">
							<a href="PCPortable.html">Ordinateur Portable</a>
						</div>

					</div>
					
					<div class="menu" id="menu2" onclick="afficheMenu(this)">
						<a href="#">Image et Son</a>
					</div>
					
					<div id="sousmenu2" style="display:none">
						<div class="sousmenu">
							<a href="TVEcran.html">TV et Ecran</a>
						</div>
						<div class="sousmenu">
							<a href="Photo.html">Appareil Photo</a>
						</div>

					</div>
					
					<div class="menu" id="menu3" onclick="afficheMenu(this)">
						<a href="#">Telephonie et Gps</a>
					</div>
					
					<div id="sousmenu3" style="display:none">
						<div class="sousmenu">
							<a href="#Telephonie.html">Telephonie</a>
						</div>
						<div class="sousmenu">
							<a href="GPS.html">GPS</a>
						</div>

					</div>
					
					<div class="menu" id="menu4" onclick="afficheMenu(this)">
						<a href="#">Jeux et Consoles</a>
					</div>
					
					<div id="sousmenu4" style="display:none">
						<div class="sousmenu">
							<a href="JeuxPC.html">PC</a>
						</div>
						<div class="sousmenu">
							<a href="JeuxPs4.html">Playstation 4</a>
						</div>
						<div class="sousmenu">
							<a href="JeuxXBOX.html">XBOX One</a>
						</div>

					</div>
					
					<div class="menu" id="menu5" onclick="afficheMenu(this)">
						<a href="Logiciel.html">Logiciels</a>
					</div>					
				
	

</div>
       		</td> 
       		<td>
       		
       		</td>

   	 </tr>
	</table>
	
		<p>
       		
       		
	</p>
	<p>
		<br /> </p><?php

    $con=mysqli_connect("localhost","root","","database-eq8");
    // Check connection
    if (mysqli_connect_errno())
      {
      echo "Failed to connect to MySQL: " . mysqli_connect_error();
      }
//query
    $result = mysqli_query($con,"SELECT * FROM Catalogue");

  echo "<table  border='1' cellspacing='4' cellpadding='10' id='matable'>";
           echo "<tr>";
		  echo"<th>ID</th><th>Marque</th><th>Nom</th><th>Famille</th><th>Dimension</th><th>Poids</th><th>Infotech</th><th>Prix</th><th>image</th></tr>";
		  echo"<tr>";
		  
 while($row = mysqli_fetch_array($result))
          {
 $n=$row['ID'];
 $direct=$row['imagepath'];
 
		  echo"<td>" . $row['ID'] . "</td>";
		  echo"<td> " . $row['Marque'] . "</td>";
		  echo"<td> " . $row['Nom'] . "</td>";
		  echo"<td> " . $row['Famille'] . "</td>";
		  echo"<td> " . $row['Dimension'] . "</td>";
		  echo"<td> " . $row['Poids'] . "</td>";
		  echo"<td> " . $row['Infotech'] . "</td>";
	      echo"<td> " . $row['Prix'] . "</td>";

		//  echo"<td> " . $row['imagepath'] . "</td>";
		  echo '<td><img onmouseout="normalImg(this)" onmouseover="bigImg(this)" src="' . $row['imagepath'] . '" alt="Image path Invalid"  witdh="10" height="80" /></td>';
		  echo"</tr>"; 
          }
 echo "</table>";


    mysqli_close($con);
    ?><p><br /> <br /> 
		<a href="Contact.html">Contactez-nous</a> - <a href="CGV.html">CGV</a>
		</p>
	<script type="text/javascript" src="functions.js"></script>
</body>
</html>
