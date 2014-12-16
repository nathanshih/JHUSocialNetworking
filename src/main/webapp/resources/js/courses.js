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

// Activated when clicking "Courses" from left navigation panel
$(document).ready(function() {

    // slide down the course selection controls
    $("#contentLeft").slideUp("slow", function() {});
    $("#contentRight").slideUp("slow", function() {
        $("#courseControls").slideDown("slow");
    });

    // Activated when clicking the "Display Courses" button from course controls
	$("#courseButton").click(function() {
        // clear contentLeft & contentRight
        $("#contentLeft").empty();
        $("#contentRight").empty();
        
        var courses = document.getElementsByTagName("option")
            [document.getElementById("courseSelect").selectedIndex].value;

        // get user student ID from session cookie
        var studentId = getCookie("studentId");
        var counter = 1;
        var content;

        if (courses === 'allCourses') {
            // AJAX request for course data
    		$.ajax({
    			url: "allCourses",
    			contentType: "application/json",
    			dataType: "json",
    			type: "GET",
    			error: function() {
    			    alert("Unable to display all courses.");
    			},
    			success: function(response) {
                    // iterate through courses and display info
                    $.each(response, function(i, course) {
                        var courseId = course.courseId.replace('.','');
                        content = $("<div class=\"contentbox\">").append(
                            $("<div class=\"contenttype\">").append(
                                $("<h2>").text(course.courseName),
                                $("<p>").text(course.courseId),
                                $("<form action=\"\" method=\"post\">").append(
                                    $("<input id=\"addToCart" + courseId + 
                                      "\" name=\"addToCart" + courseId + 
                                      "\" type=\"button\" value=\"Add to Cart\">"),
                                    $("<input id=\"markCompleted" + courseId + 
                                      "\" name=\"markCompleted" + courseId + 
                                      "\" type=\"button\" value=\"Mark Completed\">")
                                ),
                                $("<div id=\"CollapsiblePanel" + course.courseId + 
                                  "\"" + " class=\"CollapsiblePanel\">").append(
                                    $("<div class=\"CollapsiblePanelTab\">").append(
                                        $("<a href=\"#\">").append(
                                            $("<u>").text("MORE INFORMATION")
                                        )
                                    ),
                                    $("<div class=\"CollapsiblePanelContent\">").append(
                                        $("<p>").text(course.discipline),
                                        $("<p>").text("Course Description: " + course.description),
                                        $("<p>").text(course.usersCompleted + 
                                                " users completed course"),
                                        $("<p>").text(course.usersCheckedOut + 
                                                " users checked out courses")
                                    )
                                )
                            ),
                            $("<br class=\"clear_both\">")
                        );
    
                        // space the displayed courses between 2 columns
                        if (counter % 2 == 1) {
                            content.appendTo("#contentLeft");
                            // add Javascript events to modified DOM
                            $("#contentLeft").on("click", "#addToCart" + courseId, function() {
                                $.ajax({
                                    url: "addToCart",
                                    data: {
                                        "studentId": studentId,
                                        "courseId": course.courseId
                                    },
                                    type: "POST",
                                    success: function() {
                                        alert("Added course " + course.courseId + " to cart.");
                                    },
                                    error: function() {
                                        alert("Unable to add course " + course.courseId + " to cart.");
                                    }
                                });
                            });
                            $("#contentLeft").on("click", "#markCompleted" + courseId, function() {
                                $.ajax({
                                    url: "addCompletedCourse",
                                    data: {
                                        "studentId": studentId,
                                        "courseId": course.courseId
                                    },
                                    type: "POST",
                                    success: function() {
                                        alert("Marked course " + course.courseId + " as completed.");
                                    },
                                    error: function() {
                                        alert("Unable to mark course " + course.courseId + " as completed.");
                                    }
                                });
                            });
                        } else {
                            content.appendTo("#contentRight");
                            // add Javascript events to modified DOM
                            $("#contentRight").on("click", "#addToCart" + courseId, function() {
                                $.ajax({
                                    url: "addToCart",
                                    data: {
                                        "studentId": studentId,
                                        "courseId": course.courseId
                                    },
                                    type: "POST",
                                    success: function() {
                                        alert("Added course " + course.courseId + " to cart.");
                                    },
                                    error: function() {
                                        alert("Unable to add course " + course.courseId + " to cart.");
                                    }
                                });
                            });
                            $("#contentRight").on("click", "#markCompleted" + courseId, function() {
                                $.ajax({
                                    url: "addCompletedCourse",
                                    data: {
                                        "studentId": studentId,
                                        "courseId": course.courseId
                                    },
                                    type: "POST",
                                    success: function() {
                                        alert("Marked course " + course.courseId + " as completed.");
                                    },
                                    error: function() {
                                        alert("Unable to mark course " + course.courseId + " as completed.");
                                    }
                                });
                            });
                        }
                        // execute javascript for collapsible panels
                        jQuery.globalEval(
                            "var cp1 = new Spry.Widget.CollapsiblePanel(\"CollapsiblePanel" + 
                            course.courseId + "\", { contentIsOpen: false });\n");
    
                        counter = counter + 1;
                    });
    			}
    		});
        } else if (courses === "completedCourses") {
            // AJAX request for course data
            $.ajax({
                url: "getCompletedCourses",
                data: {
                    "studentId": studentId
                },
                type: "GET",
                error: function() {
                    alert("Unable to display completed courses.");
                },
                success: function(response) {
                    // iterate through courses and display info
                    $.each(response, function(i, courseLight) {
                        content = $("<div class=\"contentbox\">").append(
                            $("<div class=\"contenttype\">").append(
                                $("<h2>").text(courseLight.courseName),
                                $("<p>").text(courseLight.courseId)
                            ),
                            $("<br class=\"clear_both\">")
                        );
    
                        // space the displayed courses between 2 columns
                        if (counter % 2 == 1) {
                            content.appendTo("#contentLeft");
                        } else {
                            content.appendTo("#contentRight");
                        }
    
                        counter = counter + 1;
                    });
                }
            });
        }

        // slide out course controls
        $("#courseControls").slideUp("slow", function() {
            $("#contentLeft").slideDown("slow");
            $("#contentRight").slideDown("slow");
        });
	});

});