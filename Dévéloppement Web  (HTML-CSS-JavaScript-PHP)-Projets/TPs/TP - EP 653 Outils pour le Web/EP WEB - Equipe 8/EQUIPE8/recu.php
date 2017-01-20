<?

	include("includes/functions.php");
	
	if($_REQUEST['command']=='update'){
		$name=$_REQUEST['name'];
		$email=$_REQUEST['email'];
		$address=$_REQUEST['address'];
		$phone=$_REQUEST['phone'];
		
		$result=mysql_query("insert into customers values('','$name','$email','$address','$phone')");
		$customerid=mysql_insert_id();
		$date=date('Y-m-d');
		$result=mysql_query("insert into orders values('','$date','$customerid')");
		$orderid=mysql_insert_id();
		
		$max=count($_SESSION['cart']);
		for($i=0;$i<$max;$i++){
			$pid=$_SESSION['cart'][$i]['productid'];
			$q=$_SESSION['cart'][$i]['qty'];
			$price=get_price($pid);
			mysql_query("insert into order_detail values ($orderid,$pid,$q,$price)");
		}
		die('Thank You! your order has been placed!');
	}
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Billing Info</title>
<script language="javascript">
	function validate(){
		var f=document.form1;
		if(f.name.value==''){
			alert('Your name is required');
			f.name.focus();
			return false;
		}
		f.command.value='update';
		f.submit();
	}
</script>
</head>


<body>
<form name="form1" onsubmit="return validate()">
    <input type="hidden" name="command" />
	<div align="center">
        <h1 align="center">RECU</h1>
        <table border="0" cellpadding="2px">
        	<tr>
        	  <td>Commande Total:</td><td><?=get_order_total()?></td></tr>
            <tr>
              <td> Nom:</td><td><input type="text" name="name" /></td></tr>
            <tr>
              <td>Adresse:</td><td><input type="text" name="address" /></td></tr>
            <tr><td>Email:</td><td><input type="text" name="email" /></td></tr>
            <tr>
              <td>Telephone:</td><td><input type="text" name="phone" /></td></tr>
            <tr><td>&nbsp;</td><td><input type="submit" value="Passer la commande" /></td></tr>
        </table>
	</div>
</form>
</body>
</html>
