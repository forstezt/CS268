	function formLoad() {
		top.window.moveTo(0,0);
		top.window.resizeTo(screen.availWidth,screen.availHeight);
		document.frmLogin.f_id.focus();
	}
	function validate() {
		if(document.frmLogin.f_id.value == "") {
			alert("Please enter your UserID");
			document.frmLogin.f_id.focus();
			return false;
		}
		var pin = document.frmLogin.f_pin.value;
		if(pin == "") {
			alert("Please enter your PIN");
			document.frmLogin.f_pin.focus();
			return false;
		}		
		if(isNaN(pin)) {
			alert("Please enter a number for your PIN");
			document.frmLogin.f_pin.select();
			return false;
		}
		if(pin.length != 4 || pin.indexOf(".", 0) >= 0) {
			alert("Please enter a four digit whole number for your PIN");
			document.frmLogin.f_pin.select();
			return false;
		}		
		return true;
	}