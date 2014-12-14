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

        // AJAX request for course data
		$.ajax({
			url: courses,
			contentType: "application/json",
			dataType: "json",
			type: "GET",
			error: function() {
			    alert("Unable to display courses.");
			},
			success: function(response) {
                var counter = 1;
                var content;
                
                // iterate through courses and display info
                $.each(response, function(i, course) {
                    content = $("<div class=\"contentbox\">").append(
                        $("<div class=\"contenttype\">").append(
                            $("<h2>").text(course.courseName),
                            $("<p>").text(course.courseId),
                            $("<div id=\"CollapsiblePanel" + course.courseId + 
                              "\"" + " class=\"CollapsiblePanel\">").append(
                                $("<div class=\"CollapsiblePanelTab\">").append(
                                    $("<a href=\"#\">").append(
                                        $("<u>").text("MORE INFORMATION")
                                    )
                                ),
                                $("<div class=\"CollapsiblePanelContent\">").append(
                                    $("<p>").text(course.description),
                                    $("<p>").text(course.discipline),
                                    $("<p>").text(course.usersCompleted + 
                                            " users completed course"),
                                    $("<p>").text(course.usersCheckedOut + 
                                            " users checked out courses"),
                                    $("<form action=\"\" method=\"post\">").append(
                                        $("<input id=\"course" + course.courseId + 
                                          "\" name=\"course" + course.courseId + 
                                          "\" type=\"hidden\" value=\"" + 
                                          course.courseId + "\">"),
                                        $("<a id=\"addCart" + course.courseId + 
                                          "\" href=\"#\">").text("Add to Cart"),
                                        $("<a id=\"complete" + course.courseId + 
                                          "\" href=\"#\">").text("Mark Completed")
                                    )
                                )
                            )
                        ),
                        $("<br class=\"clear_both\">")
                    );
                    // space the displayed courses between 2 columns
                    if (counter % 2 == 1)
                        content.appendTo("#contentLeft");
                    else
                        content.appendTo("#contentRight");
                    // execute javascript for collapsible panels
                    jQuery.globalEval(
                        "var cp1 = new Spry.Widget.CollapsiblePanel(\"CollapsiblePanel" + 
                        course.courseId + "\", { contentIsOpen: false });\n");

                    counter = counter + 1;
                });
			}
		});

        // slide out course controls
        $("#courseControls").slideUp("slow", function() {
            $("#contentLeft").slideDown("slow");
            $("#contentRight").slideDown("slow");
        });
	});

});