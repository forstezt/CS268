function getPreferences() {
	getBgColor();
	getBorderWidth();
}
function getBgColor() {
	var bgcolor = getCookie("bgColor");
	if(bgcolor != "") {
		document.body.bgColor = bgcolor;		
	}			
}
function setBgColor() {
  	if(document.frmCustomer.selBgColor.value != "Select") {	
		document.body.bgColor = "#" + document.frmCustomer.selBgColor.value;
		document.cookie = "bgColor=" + document.frmCustomer.selBgColor.value + ";expires=Wednesday, 02-Mar-2020 12:00:00 GMT;";
	} 
}
function setCustomBgColor() {
	document.body.bgColor = "#" + document.frmCustomer.txtBgColor.value;
	document.cookie = "bgColor=" + document.frmCustomer.txtBgColor.value + ";expires=Wednesday, 02-Mar-2020 12:00:00 GMT;";		
}
function getBorderWidth() {
	var borderWidth = getCookie("borderWidth");
	if(borderWidth != "") {
		var mainTable = document.getElementById("mainTable");
		mainTable.border = borderWidth;
	}
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