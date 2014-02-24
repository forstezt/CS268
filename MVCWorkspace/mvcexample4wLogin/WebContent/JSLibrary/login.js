	function formLoad() {
		document.frmLogin.username.focus();
	}
	function validate() {
		if(document.frmLogin.username.value == "") {
			alert("Please enter your Username");
			document.frmLogin.username.focus();
			return false;
		}
		var pass = document.frmLogin.password.value;
		if(pass == "") {
			alert("Please enter your password");
			document.frmLogin.password.focus();
			return false;
		}	
		return true;
	}