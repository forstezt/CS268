function formLoad() {
	document.frmCourse.c_callid.focus();
}
function validate() {
	if(document.frmCourse.c_callid.value == "") {
		alert("Please enter the course call id");
		document.frmCourse.c_callid.focus();
		return false;
	}
	if(document.frmCourse.c_name.value == "") {
		alert("Please enter the course name");
		document.frmCourse.c_name.focus();
		return false;
	}
	var credits = document.frmCourse.c_credits.value;
	if(credits == "") {
		alert("Please enter the number of course c_credits");
		document.frmCourse.c_credits.focus();
		return false;
	}
	if(isNaN(credits)) {
		alert("Please enter a number for course c_credits");
		document.frmCourse.c_credits.select();
		return false;
	}
	if(credits < 0 || credits > 10) {
		alert("Please enter a number between 0 and 10 for course c_credits");
		document.frmCourse.c_credits.select();
		return false;	
	}
	if(document.frmCourse.d_id.selectedIndex == -1) {
		alert("Please select the department");
		document.frmCourse.d_id.focus();
		return false;
	}
	return true;
}