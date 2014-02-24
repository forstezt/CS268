<?php 
$bgColor = $_COOKIE["bgColor"];
$borderWidth = $_COOKIE["borderWidth"];
// wait until after attempt to retrieve bgColor and borderWidth to turn this on
error_reporting(E_ALL);

if(is_null($bgColor)) {
	// set bgColor to the default
	$bgColor = "9999CC";
}
if(is_null($borderWidth)) {
	// set borderWidth to the default
	$borderWidth = 5;
}
?>
<html><head><title>Candy Customers</title></head>
<body bgcolor="#<?php echo $bgColor ?>">
<table align="center" border="<?php echo $borderWidth ?>" cellspacing="1" cellpadding="5">
<tr>
  <td rowspan="2" valign="top">
	<div style="border:ridge"><img src="candy.jpg" alt="Candy Customers" width="100px" height="100px"></div>
  </td>
  <td>bgColor=<?php echo $bgColor; ?></td>
</tr>
<tr>
  <td>borderWidth=<?php echo $borderWidth; ?></td>
</tr>
</table>
</body>
</html>