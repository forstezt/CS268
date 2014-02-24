var topOfDiv1 = 0;
var leftOfDiv1 = 0;
var moving = false;
var divObj1;
var divObj2;
var divObj3;

// the right way to do this
function startMoving1() {
	divObj1 = document.getElementById("movethis1");
	moveDiv1();
}

function moveDiv1() {
	topOfDiv1 += 1;
	leftOfDiv1 += 1;
	divObj1.style.top = topOfDiv1 + "px";
	divObj1.style.left = leftOfDiv1 + "px";
	if(topOfDiv1 < 300) {
		setTimeout("moveDiv1()", 10);
	} 
}

// this doesn't show any movement other than the starting and ending position
function startMoving2() {
	divObj2 = document.getElementById("movethis2");
	moveDiv2();
}

function moveDiv2() {
	for(var i = 0; i <= 300; i++) {
		divObj2.style.top = i + "px";
		divObj2.style.left = i + 90 + "px";			
	}
}

// the movement isn't as smooth as the first approach
function startMoving3() {
	divObj3 = document.getElementById("movethis3");
	moveDiv3();
}

function moveDiv3() {
	for(var i = 0; i <= 300; i++) {
		setTimeout("positionDiv3(" + i + ")", 10 * i);
	} 
}

function positionDiv3(pos) {
	divObj3.style.top = pos + "px";
	divObj3.style.left = pos + 180 + "px";				
}

function startAll() {
	topOfDiv1 = 0;
	leftOfDiv1 = 0;
	startMoving1();
	startMoving2();
	startMoving3();	
}