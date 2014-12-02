$(document).ready(function() {

    // Activate when clicking "Home" from left navigation panel
    $("#home").click(function() {
        // clear contentLeft & contentRight
        $("#contentLeft").empty();
        $("#contentRight").empty();
        
        // slide out profileForm if it is present
        $("#profileForm").slideUp("slow", function() {
            $("#contentLeft").slideDown("slow");
            $("#contentRight").slideDown("slow");
        });
    });

    // Activate when clicking "Courses" from left navigation panel
	$("#courses").click(function() {
        // clear contentLeft & contentRight
        $("#contentLeft").empty();
        $("#contentRight").empty();

        // AJAX request for student data
		$.ajax({
			url: "courses",
			contentType: "application/json",
			dataType: "json",
			type: "GET",
			success: function(response) {
                var counter = 1;
                var jsString = "";
                var content;

                // slide out profileForm if it is present
                $("#profileForm").slideUp("slow", function() {
                    $("#contentLeft").slideDown("slow");
                    $("#contentRight").slideDown("slow");
                });
                
                // iterate through students and display info
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
                    // space the displayed students between 2 columns
                    if (counter % 2 == 1)
                        content.appendTo("#contentLeft");
                    else
                        content.appendTo("#contentRight");
                    // build javascript string for collapsible panels
                    jsString = jsString + "var cp" + counter + 
                      " = new Spry.Widget.CollapsiblePanel(\"CollapsiblePanel" + 
                      counter + "\", { contentIsOpen: false });\n";

                    counter = counter + 1;
                });
                
                // set javascript string
                $("#javascript").text(jsString);
			}
		});
	});
	
    // Activate when clicking "Cart" from left navigation panel
	$("#cart").click(function() {
        // clear contentLeft & contentRight
        $("#contentLeft").empty();
        $("#contentRight").empty();

        // AJAX request for student data
        $.ajax({
            url: "courses",
            contentType: "application/json",
            dataType: "json",
            type: "GET",
            success: function(response) {
                var counter = 1;
                var jsString = "";
                var content;

                // slide out profileForm if it is present
                $("#profileForm").slideUp("slow", function() {
                    $("#contentLeft").slideDown("slow");
                    $("#contentRight").slideDown("slow");
                });
                
                // iterate through students and display info
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
                    // space the displayed students between 2 columns
                    if (counter % 2 == 1)
                        content.appendTo("#contentLeft");
                    else
                        content.appendTo("#contentRight");
                    // build javascript string for collapsible panels
                    jsString = jsString + "var cp" + counter + 
                      " = new Spry.Widget.CollapsiblePanel(\"CollapsiblePanel" + 
                      counter + "\", { contentIsOpen: false });\n";

                    counter = counter + 1;
                });
                
                // set javascript string
                $("#javascript").text(jsString);
            }
        });
	});
	
	// Activate when clicking "Classmates" from left navigation panel
	$("#classmates").click(function() {
        // clear contentLeft & contentRight
        $("#contentLeft").empty();
        $("#contentRight").empty();
        
        // AJAX request for student data
		$.ajax({
			url: "students",
			contentType: "application/json",
			dataType: "json",
			type: "GET",
			success: function(response) {
			    var counter = 1;
			    var jsString = "";
			    var content;

			    // slide out profileForm if it is present
                $("#profileForm").slideUp("slow", function() {
                    $("#contentLeft").slideDown("slow");
                    $("#contentRight").slideDown("slow");
                });
                
                // iterate through students and display info
				$.each(response, function(i, student) {
					content = $("<div class=\"contentbox\">").append(
						$("<div class=\"contenttype\">").append(
							$("<h2>").text(student.name),
	                        $("<div id=\"CollapsiblePanel" + counter + 
                              "\"" + " class=\"CollapsiblePanel\">").append(
                                $("<div class=\"CollapsiblePanelTab\">").append(
                                    $("<a href=\"#\">").text("MORE INFORMATION")
                                ),
                                $("<div class=\"CollapsiblePanelContent\">").append(
                                    $("<p>").text(student.email),
                                    $("<p>").text(student.degreeProgram)
                                )
	                        )
		                ),
		                $("<br class=\"clear_both\">")
				    );
					// space the displayed students between 2 columns
					if (counter % 2 == 1)
					    content.appendTo("#contentLeft");
					else
					    content.appendTo("#contentRight");
					// build javascript string for collapsible panels
					jsString = jsString + "var cp" + counter + 
					  " = new Spry.Widget.CollapsiblePanel(\"CollapsiblePanel" + 
					  counter + "\", { contentIsOpen: false });\n";

					counter = counter + 1;
				});
                
                // set javascript string
                $("#javascript").text(jsString);
			}
		});
	});

    // Activate when clicking "Profile" from left navigation panel
    $("#profile").click(function() {
        // slide out content panels if they are present
        $("#contentLeft").slideUp("slow", function() {});
        $("#contentRight").slideUp("slow", function() {
            $("#profileForm").slideDown("slow");
        });
    });

    $("#update").click(function() {
        var name = $("#name").val();
    });
});