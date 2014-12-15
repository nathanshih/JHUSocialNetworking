// used from w3schools.com
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

// Activated when clicking "Classmates" from left navigation panel
$(document).ready(function() {

    // clear contentLeft & contentRight
    $("#contentLeft").empty();
    $("#contentRight").empty();
    
    // AJAX request for student data
	$.ajax({
		url: "students",
		contentType: "application/json",
		dataType: "json",
		type: "GET",
        error: function() {
            alert("Unable to display classmates.");
        },
		success: function(response) {
		    var counter = 1;
		    var content;

            // iterate through students and display info
			$.each(response, function(i, student) {
				content = $("<div class=\"contentbox\">").append(
					$("<div class=\"contenttype\">").append(
						$("<h2>").text(student.name),
                        $("<div id=\"CollapsiblePanel" + student.id + 
                          "\"" + " class=\"CollapsiblePanel\">").append(
                            $("<div class=\"CollapsiblePanelTab\">").append(
                                $("<a href=\"#\">").append(
                                    $("<u>").text("MORE INFORMATION")
                                )
                            ),
                            $("<div class=\"CollapsiblePanelContent\">").append(
                                $("<p>").text("Major: " + student.discipline),
                                $("<p>").text("Courses completed: " + 
                                              student.completedCourses.length),
                                $("<form action=\"\" method=\"post\">").append(
                                    $("<input id=\"student" + student.id + 
                                      "\" name=\"student" + student.id + 
                                      "\" type=\"hidden\" value=\"" + 
                                      student.email + "\">"),
                                    $("<input id=\"email" + student.id + 
                                      "\" name=\"email" + student.id + 
                                      "\" type=\"button\" value=\"Send Email\">")
                                )
                            )
                        )
	                ),
	                $("<br class=\"clear_both\">")
			    );

                // get user student ID from session cookie
                var studentId = getCookie("studentId");

				// space the displayed students between 2 columns
				if (counter % 2 == 1) {
				    content.appendTo("#contentLeft");
				    $("#contentLeft").on("click", "#email" + student.id, function() {
                        $.ajax({
                            url: "emailStudent",
                            data: {
                                "studentId": studentId,
                                "toEmail": $("#student" + student.id).val()
                            },
                            type: "POST",
                            success: function() {
                                alert("Student successfully emailed.");
                            },
                            error: function() {
                                alert("Unable to email student.");
                            }
                        });
				    });
				} else {
				    content.appendTo("#contentRight");
                    $("#contentRight").on("click", "#email" + student.id, function() {
                        $.ajax({
                            url: "emailStudent",
                            data: {
                                "studentId": studentId,
                                "toEmail": $("#student" + student.id).val()
                            },
                            type: "POST",
                            success: function() {
                                alert("Student successfully emailed.");
                            },
                            error: function() {
                                alert("Unable to email student.");
                            }
                        });
                    });
			    }
				// execute javascript for collapsible panels
				jQuery.globalEval(
				    "var cp1 = new Spry.Widget.CollapsiblePanel(\"CollapsiblePanel" + 
		            student.id + "\", { contentIsOpen: false });\n");

				counter = counter + 1;
			});
		}
	});
});