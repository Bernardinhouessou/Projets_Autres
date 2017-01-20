<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="style.css" />
        <title>Accueil BigDeal.fr</title>
    
    <!-- styles CSS pour mon recherche input type -->
<style type="text/css">
	#tfheader{
		background-color:#c3dfef;//couleur bleu claire
	}
	#tfnewsearch{
		float:right;
		padding:10px;
	}
	.tftextinput{
		margin: 0;
		padding: 5px 20px;
		font-family: Arial, Helvetica, sans-serif;
		font-size:14px;
		border:1px solid #0076a3; border-right:0px;
		border-top-left-radius: 5px 5px;
		border-bottom-left-radius: 5px 5px;
	}
	.tfbutton {
		margin: 0;
		padding: 5px 15px;
		font-family: Arial, Helvetica, sans-serif;
		font-size:14px;
		outline: none;
		cursor: pointer;
		text-align: center;
		text-decoration: none;
		color: #ffffff;
		border: solid 1px #0076a3; border-right:0px;
		background: #0095cd;
		background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));
		background: -moz-linear-gradient(top,  #00adee,  #0078a5);
		border-top-right-radius: 5px 5px;
		border-bottom-right-radius: 5px 5px;
	}
	.tfbutton:hover {
		text-decoration: none;
		background: #007ead;
		background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));
		background: -moz-linear-gradient(top,  #0095cc,  #00678e);
	}
	/* Fixer les bouttons submit  largeur(taille....)*/
	.tfbutton::-moz-focus-inner {
	  border: 0;
	}
	.tfclear{
		clear:both;
	}
</style>
    </head>

    <body>
   
    
<?php ?>    
    	<a id=loginpart name='loginpart' href="login.php">Login</a>
        <!-- HTML for SEARCH BAR -->
	<div id="tfheader">
	  <form id="tfnewsearch" method="POST" action="search.php">
		        <select id="SearchCmb" name="search12">
        <option value="">Tous les Produits</option>
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
      <input type="search" class="tftextinput" name="search" size="21" maxlength="120"><input type="submit" value="RECHERCHER" class="tfbutton">
    </form><H1 ><span><a href="Acceuil.php">BigDeal.fr</a></span></H1>
	  <div class="tfclear"></div>
	</div>
    <H2>Accueil</H2>
    <table>
  	 	 <tr>
       		<td>
       			<div id="menu">
					
					<div class="menu" id="menu1" onclick="afficheMenu(this)">
						<a href="#">Informatique</a>
					</div>
					
					<div id="sousmenu1" style="display:none">
						<div class="sousmenu">
							<a name='search4' id='search4' href="searchdi.php?ordB=Ordinateur de Bureau">Ordinateur de Bureau</a>
						</div>
						<div class="sousmenu">
							<a name='search5' id='search5' href="searchdi.php?ordB=Ordinateur Portable">Ordinateur Portable</a>
						</div>

					</div>
					
					<div class="menu" id="menu2" onclick="afficheMenu(this)">
						<a href="#">Image et Son</a>
					</div>
					
					<div id="sousmenu2" style="display:none">
						<div class="sousmenu">
							<a name='search6' id='search6' href="searchdi.php?ordB=TV et Ecran">TV et Ecran</a>
						</div>
						<div class="sousmenu">
							<a name='search7' id='search7'href="searchdi.php?ordB=Appareil Photo">Appareil Photo</a>
						</div>

					</div>
					
					<div class="menu" id="menu3" onclick="afficheMenu(this)">
						<a href="#">Telephonie et Gps</a>
					</div>
					
					<div id="sousmenu3" style="display:none">
						<div class="sousmenu">
							<a name='search8' id='search8'href="searchdi.php?ordB=Telephonie">Telephonie</a>
						</div>
						<div class="sousmenu">
							<a name='search9' id='search9'href="searchdi.php?ordB=GPS">GPS</a>
						</div>

					</div>
					
					<div class="menu" id="menu4" onclick="afficheMenu(this)">
						<a href="#">Jeux et Consoles</a>
					</div>
					
					<div id="sousmenu4" style="display:none">
						<div class="sousmenu">
							<a name='search10' id='search10' href="searchdi.php?ordB=PC">PC</a>
						</div>
						<div class="sousmenu">
							<a name='search11' id='search11' href="searchdi.php?ordB=Playstation 4">Playstation 4</a>
						</div>
						<div class="sousmenu">
							<a name='search12' id='search12' href="searchdi.php?ordB=XBOX One">XBOX One</a>
						</div>

					</div>
					
			  <div class="menu" id="menu5" onclick="afficheMenu(this)">
						<a name='search13' id='search13' href="searchdi.php?ordB=Logiciels">Logiciels</a>
			  </div>			
                    <div>
            <h2></h2></td> 
       		
       		<td>
       					
       		</td>

       		<td id='show' name='show'>
      		
       		<p>
       		Bienvenue sur notre catalogue en ligne de produits High Tech.<br /> 
       		Vous trouverez sur notre site une multitude de produits de dernière génération.<br />
       		<br /><br />
       		<MARQUEE onmouseover="this.stop();" onmouseout="this.start();" 
       		style="WIDTH: 470px; HEIGHT: 225px" scrollDelay=110>
				<IMG style="hspace: 100%; HEIGHT: 215px" src="PCportable.jpg">
				<IMG style="hspace: 100%; HEIGHT: 215px" src="Photo.jpg">
			</MARQUEE>
		</p>
        
       		</td>

   	 </tr>
	</table>
			
		<p>
		<br /> <br /> <br /> 
		<a href="Contact.html">Contactez-nous</a> - <a href="CGV.php">CGV</a>
		</p>
	<script type="text/javascript" src="functions.js"></script>		
    </body>
</html>