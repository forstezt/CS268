<?php error_reporting(E_ALL); ?>
<html><head><title>Arrays</title></head>
<body>
<?php
	$myArray[0] = "one";
	$myArray[1] = "two";
	$myArray[2] = "three";
	$length = count($myArray);
	for($i = 0; $i < $length; $i++) {
		echo $myArray[$i] . "<br />";	
	}
	foreach ($myArray as $index => $value) {
		echo $index . " - " . $value . "<br />";	
	}
	foreach ($myArray as $value) {
		echo $value . "<br />";	
	}
	echo count($myArray) . "<br />";
?>
</body></html>

