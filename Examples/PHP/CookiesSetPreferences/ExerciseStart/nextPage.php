<?php 
$bgColor = $_COOKIE["bgColor"];
$borderWidth = $_COOKIE["borderWidth"];
// wait until after attempt to retrieve bgColor and borderWidth to turn this on
error_reporting(E_ALL);

// check $bgColor to see if it is null, if so, assign "9999CC" to $bgColor
// in other words "9999CC" is the default color if a cookie isn't found

// check $borderWidth to see if it is null, if so, assign 5 to $borderWidth
// in other words 5 is the default border width if a cookie isn't found


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