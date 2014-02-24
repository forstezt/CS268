<?php 
	session_start();
	error_reporting(E_ALL);
	$item = $_REQUEST["part_number"];
	unset($_SESSION[$item]);
	header("Location:SessionViewCart.php");
?>
