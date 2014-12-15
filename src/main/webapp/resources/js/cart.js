// used from http://www.w3schools.com/js/js_cookies.asp
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}

// Activated when clicking "Cart" from left navigation panel
$(document).ready(function() {

    // clear contentLeft & contentRight
    $("#contentLeft").empty();
    $("#contentRight").empty();

    // get user student ID from session cookie
    var studentId = getCookie("studentId");
    var counter = 1;
    var content;

    // AJAX request for cart data
    $.ajax({
        url: "checkedOutCourses",
        data: {
            "studentId": studentId
        },
        type: "GET",
        error: function() {
            alert("Unable to display cart.");
        },
        success: function(response) {
            // iterate through cart and display
            $.each(response, function(i, courseLight) {
                var courseId = courseLight.courseId.replace('.','');
                content = $("<div class=\"contentbox\">").append(
                    $("<div class=\"contenttype\">").append(
                        $("<h2>").text(courseLight.courseName),
                        $("<p>").text(courseLight.courseId),
                        $("<form action=\"\" method=\"post\">").append(
                            $("<input id=\"removeCourse" + courseId + 
                              "\" name=\"removeCourse" + courseId + 
                              "\" type=\"button\" value=\"Remove\">")
                        )
                    ),
                    $("<br class=\"clear_both\">")
                );

                // space the displayed courses between 2 columns
                if (counter % 2 == 1) {
                    content.appendTo("#contentLeft");
                    $("#contentLeft").on("click", "#removeCourse" + courseId, function() {
                        $.ajax({
                            url: "removeFromCart",
                            data: {
                                "studentId": studentId,
                                "courseId": courseLight.courseId
                            },
                            type: "DELETE",
                            success: function() {
                                alert("Course deleted from cart.");
                            },
                            error: function() {
                                alert("Unable to delete course from cart.");
                            }
                        });
                    });
                } else {
                    content.appendTo("#contentRight");
                    $("#contentRight").on("click", "#removeCourse" + courseId, function() {
                        $.ajax({
                            url: "removeFromCart",
                            data: {
                                "studentId": studentId,
                                "courseId": courseLight.courseId
                            },
                            type: "DELETE",
                            success: function() {
                                alert("Course deleted from cart.");
                            },
                            error: function() {
                                alert("Unable to delete course from cart.");
                            }
                        });
                    });
                }

                counter = counter + 1;
            });
        }
    });
});