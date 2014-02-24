function formLoad(obj) {
	obj.focus();
}
function validate() {
	obj = document.frmCourse;
	if(obj["frmCourse:c_callid"].value == "") {
		alert("Please enter the course call id");
		obj["frmCourse:c_callid"].focus();
		return false;
	}
	if(obj["frmCourse:c_name"].value == "") {
		alert("Please enter the course name");
		obj["frmCourse:c_name"].focus();
		return false;
	}
	var credits = obj["frmCourse:c_credits"].value;
	if(credits == "") {
		alert("Please enter the number of course c_credits");
		obj["frmCourse:c_credits"].focus();
		return false;
	}
	testNum = /^[0-9]+$/; 	
	if(testNum.test(credits) == false) {
		alert("Please enter a number for course c_credits (no decimals allowed)");
		obj["frmCourse:c_credits"].select();
		return false;
	}
	if(credits < 0 || credits > 10) {
		alert("Please enter a number between 0 and 10 for course c_credits");
		obj["frmCourse:c_credits"].select();
		return false;	
	}
	return confirm("OK to continue");
}