     // pre-cache images
    var homeUp = new Image();
    homeUp.src = "Images/Buttons/homeUp.jpg";
    var homeDown = new Image();
    homeDown.src = "Images/Buttons/homeDown.jpg";
    var homeOver = new Image();
    homeOver.src = "Images/Buttons/homeOver.jpg";

    var coursesUp = new Image();
    coursesUp.src = "Images/Buttons/coursesUp.jpg";
    var coursesDown = new Image();
    coursesDown.src = "Images/Buttons/coursesDown.jpg";
    var coursesOver = new Image();
    coursesOver.src = "Images/Buttons/coursesOver.jpg";

    var addUp = new Image();
    addUp.src = "Images/Buttons/addUp.jpg";
    var addDown = new Image();
    addDown.src = "Images/Buttons/addDown.jpg";
    var addOver = new Image();
    addOver.src = "Images/Buttons/addOver.jpg";

    function changeImg(button, newImage) {
        button.src = newImage.src;
    }
    function home() {
		window.location.href = "login.jsp";
    }
    function courses() {
		window.location.href = "displayCourses.jsp";
    }
    function add() {
		window.location.href = "courseForm.jsp";
    }