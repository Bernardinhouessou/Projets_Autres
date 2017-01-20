<!DOCTYPE >
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shopping Cart</title>
<script language="javascript">
	function del(pid){
		if(confirm('Do you really mean to delete this item')){
			document.form1.pid.value=pid;
			document.form1.command.value='delete';
			document.form1.submit();
		}
	}
	function clear_cart(){
		if(confirm('This will empty your shopping cart, continue?')){
			document.form1.command.value='clear';
			document.form1.submit();
		}
	}
	function update_cart(){
		document.form1.command.value='update';
		document.form1.submit();
	}


</script>
</head>

<body>



<?
/*

	include("includes/functions.php");
	
	if($_REQUEST['command']=='delete' && $_REQUEST['pid']>0){
		remove_product($_REQUEST['pid']);
	}
	else if($_REQUEST['command']=='clear'){
		unset($_SESSION['cart']);
	}
	else if($_REQUEST['command']=='update'){
		$max=count($_SESSION['cart']);
		for($i=0;$i<$max;$i++){
			$pid=$_SESSION['cart'][$i]['productid'];
			$q=intval($_REQUEST['product'.$pid]);
			if($q>0 && $q<=999){
				$_SESSION['cart'][$i]['qty']=$q;
			}
			else{
				$msg='Un probleme avec la quantite!, Choississer une quantite entre les nombres 1 et 999';
			}
		}
	}
*/
?>




<form name="form1" method="post">
<input type="hidden" name="pid" />
<input type="hidden" name="command" />
	<div style="margin:0px auto; width:600px;" >
    <div style="padding-bottom:10px">
    	<h1 align="center">VOTRE PANIER</h1>
    <input type="button" value="Continuer ses achats" onclick="window.location='products.php'" />
    </div>
    	<div style="color:#F00"><?=$msg?></div>
    	<table border="0" cellpadding="5px" cellspacing="1px" style="font-family:Verdana, Geneva, sans-serif; font-size:11px; background-color:#E1E1E1" width="100%">
    	<?
			if(is_array($_SESSION['cart'])){
            	echo '<tr bgcolor="#FFFFFF" style="font-weight:bold"><td>Serial</td><td>Name</td><td>Price</td><td>Qty</td><td>Amount</td><td>Options</td></tr>';
				$max=count($_SESSION['cart']);
				for($i=0;$i<$max;$i++){
					$pid=$_SESSION['cart'][$i]['productid'];
					$q=$_SESSION['cart'][$i]['qty'];
					$pname=get_product_name($pid);
					if($q==0) continue;
			?>
            		<tr bgcolor="#FFFFFF"><td><?=$i+1?></td><td><?=$pname?></td>
                    <td><big style="color:green">€</big>                      <?=get_price($pid)?></td>
                    <td><input type="text" name="product<?=$pid?>" value="<?=$q?>" maxlength="3" size="2" /></td>                    
                    <td><big style="color:green">€</big>                      <?=get_price($pid)*$q?></td>
                    <td><a href="javascript:del(<?=$numID?>)">Enlever</a></td></tr>
            <?					
				}
			?>
				<tr>
          <td><b>Prix Total: 
                <big style="color:green">€</big>
          <?=get_order_total()?></b></td><td colspan="5" align="right"><input type="button" value="Vider le Panier" onclick="clear_cart()"><input type="button" value="Mise a jour du Panier" onclick="update_cart()"><input type="button" value="Passer la commande" onclick="window.location='billing.php'"></td></tr>
			<?
            }
			else{
				echo "<tr bgColor='#FFFFFF'><td>There are no items in your shopping cart!</td>";
			}
		?>
        </table>
    </div>
</form>


</body>
</html>