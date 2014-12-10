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
		success: function(response) {
		    var counter = 1;
		    var jsString = "";
		    var content;

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