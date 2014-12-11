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
        
        var courses = document.getElementsByTagName("option")[document.getElementById("courseSelect").selectedIndex].value;

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
                var jsString = "";
                var content;
                
                // iterate through courses and display info
                $.each(response, function(i, course) {
                    content = $("<div class=\"contentbox\">").append(
                        $("<div class=\"contenttype\">").append(
                            $("<h2>").text(course.courseName),
                            $("<p>").text(course.courseId),
                            $("<div id=\"CollapsiblePanel" + counter + 
                              "\"" + " class=\"CollapsiblePanel\">").append(
                                $("<div class=\"CollapsiblePanelTab\">").append(
                                    $("<a href=\"#\">").text("MORE INFORMATION")
                                ),
                                $("<div class=\"CollapsiblePanelContent\">").append(
                                    $("<p>").text(course.courseDescription)
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
                    // build javascript string for collapsible panels
                    jsString = jsString + "var cp1" + 
                      " = new Spry.Widget.CollapsiblePanel(\"CollapsiblePanel" + 
                      counter + "\", { contentIsOpen: false });\n";

                    counter = counter + 1;
                });
                
                // set javascript string
                $("#javascript").text(jsString);
			}
		});

        // slide out course controls
        $("#courseControls").slideUp("slow", function() {
            $("#contentLeft").slideDown("slow");
            $("#contentRight").slideDown("slow");
        });
	});

});