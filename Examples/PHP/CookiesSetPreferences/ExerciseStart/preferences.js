function getPreferences() {
	getBgColor();
	getBorderWidth();
}
function setBgColor() {
  	if(document.frmCustomer.selBgColor.value != "Select") {	
		document.body.bgColor = "#" + document.frmCustomer.selBgColor.value;
		document.cookie = "bgColor=" + document.frmCustomer.selBgColor.value + ";expires=Wednesday, 02-Mar-2020 12:00:00 GMT;";
	} 
}
function setCustomBgColor() {
	// don't bother with error checking
	// code is similar to that in setBgColor function
	// but concatenate in what the user entered in the text input, not the select list
	// and set the cookie using the text input, not the select list
	
}
function getBgColor(c) {
	var bgcolor = getCookie("bgColor");
	if(bgcolor != "") {
		document.body.bgColor = bgcolor;		
	}			
}
function getBorderWidth(w) {
	// code is simlar to that in getBgColor function
	// but look for a cookie named borderWidth, not bgColor
	// and use getElementById (as used in setBorderWith function)
	// to set the mainTable's border
	
}
function setBorderWidth() {
	if(document.frmCustomer.selBorderWidth.value != "Select") {	
		var mainTable = document.getElementById("mainTable");
		mainTable.border = document.frmCustomer.selBorderWidth.value;
		document.cookie = "borderWidth=" + document.frmCustomer.selBorderWidth.value + ";expires=Wednesday, 02-Mar-2020 12:00:00 GMT;";
	} 
}
function getCookie(strName) {
	var numEndOfCookie;
	var Result = "";
	var allCookies = document.cookie;
	var SearchName = strName + "=";
	var StartOfCookie = allCookies.indexOf(SearchName)
	if (StartOfCookie != -1) {
		StartOfCookie += SearchName.length;
		EndOfCookie = allCookies.indexOf(";", StartOfCookie);
		if (EndOfCookie == -1) {
			EndOfCookie = allCookies.length;
		}
		Result = allCookies.substring(StartOfCookie, EndOfCookie);
	}
	return Result;
}    