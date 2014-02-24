var showFileName = true; // = true to show the filename in the popuped photo, = false to hide it
$(function() {
    $("#popupphoto").click(function() {
        $("#popupphoto").fadeOut(1000);
    }); 	
    $(".image").mouseover(function() { 
        $(this).css("border", "3px solid #9F0");
    })
    $(".image").mouseout(function() {
        $(this).css("border", "3px solid #000000");
    });
    $(".image").click(function() {
        var path = getLargePhotoPath($(this).attr("src"));
        $("#imgphoto").attr("src", path);
        $("#popupphoto").slideDown(1000);        
        if(showFileName == true) {
            $("#filename").text(getFilename(path));
        }
    }); 
});
function getLargePhotoPath(path) {
    // this requires naming the files in thumbnails the same as files in sizedPhotos
    return "sizedPhotos/" + path.substring(path.lastIndexOf("/") + 1);
}
function getFilename(path) {
	// Don't want to show the file extension (.jpg, .gif, etc.) and don't want to show the full path (sizedPhotos/)
	return path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
}