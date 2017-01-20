<!DOCTYPE html>
<html>
<head>
<title>Search Box Example 1</title>
<meta name="ROBOTS" content="NOINDEX, NOFOLLOW" />
<!-- CSS styles for standard search box -->
<style type="text/css">
	#tfheader{
		background-color:#c3dfef;
	}
	#tfnewsearch{
		float:right;
		padding:20px;
	}
	.tftextinput{
		margin: 0;
		padding: 5px 15px;
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
	/* Fixes submit button height problem in Firefox */
	.tfbutton::-moz-focus-inner {
	  border: 0;
	}
	.tfclear{
		clear:both;
	}
</style>
</head>
<body>
	<!-- HTML for SEARCH BAR -->
	<div id="tfheader">
	  <form id="tfnewsearch" method="get" action="search.php">
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
		</form>
	<div class="tfclear"></div>
	</div>
</body>
</html>