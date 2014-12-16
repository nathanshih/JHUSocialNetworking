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

// Activated when clicking "Classmates" from left navigation panel
$(document).ready(function() {

    // slide down the classmate selection controls
    $("#contentLeft").slideUp("slow", function() {});
    $("#contentRight").slideUp("slow", function() {
        $("#classmateControls").slideDown("slow");
    });

    // Activated when clicking the "Display Classmates" button from classmate controls
    $("#classmateButton").click(function() {
        // clear contentLeft & contentRight
        $("#contentLeft").empty();
        $("#contentRight").empty();
        
        var classmates = document.getElementsByTagName("option")
        [document.getElementById("classmateSelect").selectedIndex].value;

        // get user student ID from session cookie
        var studentId = getCookie("studentId");
        var counter = 1;
        var content;

        if (classmates === 'students') {
            // AJAX request for classmate data
        	$.ajax({
        		url: "students",
        		contentType: "application/json",
        		dataType: "json",
        		type: "GET",
                error: function() {
                    alert("Unable to display classmates.");
                },
        		success: function(response) {
                    // iterate through students and display info
        			$.each(response, function(i, student) {
        				content = $("<div class=\"contentbox\">").append(
        					$("<div class=\"contenttype\">").append(
        						$("<h2>").text(student.name),
                                $("<form action=\"\" method=\"post\">").append(
                                    $("<input id=\"student" + student.id + 
                                      "\" name=\"student" + student.id + 
                                      "\" type=\"hidden\" value=\"" + 
                                      student.email + "\">"),
                                    $("<input id=\"addContact" + student.id + 
                                      "\" name=\"addContact" + student.id + 
                                      "\" type=\"button\" value=\"Add Contact\">"),
                                    $("<input id=\"email" + student.id + 
                                      "\" name=\"email" + student.id + 
                                      "\" type=\"button\" value=\"Send Email\">")
                                ),
                                $("<div id=\"CollapsiblePanel" + student.id + 
                                  "\"" + " class=\"CollapsiblePanel\">").append(
                                    $("<div class=\"CollapsiblePanelTab\">").append(
                                        $("<a href=\"#\">").append(
                                            $("<u>").text("MORE INFORMATION")
                                        )
                                    ),
                                    $("<div class=\"CollapsiblePanelContent\">").append(
                                        $("<p>").text("Email Address: " + student.email),
                                        $("<p>").text("Major: " + student.discipline),
                                        $("<p>").text("Courses completed: " + 
                                                      student.completedCourses.length)
                                    )
                                )
        	                ),
        	                $("<br class=\"clear_both\">")
        			    );
        
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
                                        alert("Emailed student " + student.name + " successfully.");
                                    },
                                    error: function() {
                                        alert("Unable to email student " + student.name + ".");
                                    }
                                });
        				    });
                            $("#contentLeft").on("click", "#addContact" + student.id, function() {
                                $.ajax({
                                    url: "addContact",
                                    data: {
                                        "studentId": studentId,
                                        "contactId": student.id
                                    },
                                    type: "POST",
                                    success: function() {
                                        alert("Added student " + student.name + " to contacts.");
                                    },
                                    error: function() {
                                        alert("Unable to add student " + student.name + " to contacts.");
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
                                        alert("Emailed student " + student.name + " successfully.");
                                    },
                                    error: function() {
                                        alert("Unable to email student " + student.name + ".");
                                    }
                                });
                            });
                            $("#contentRight").on("click", "#addContact" + student.id, function() {
                                $.ajax({
                                    url: "addContact",
                                    data: {
                                        "studentId": studentId,
                                        "contactId": student.id
                                    },
                                    type: "POST",
                                    success: function() {
                                        alert("Added student " + student.name + " to contacts.");
                                    },
                                    error: function() {
                                        alert("Unable to add student " + student.name + " to contacts.");
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
        } else if (classmates === 'getAllContacts'){
            // AJAX request for classmate data
            $.ajax({
                url: "getAllContacts",
                data: {
                    "studentId": studentId
                },
                type: "GET",
                error: function() {
                    alert("Unable to display classmates.");
                },
                success: function(response) {
                    // iterate through email addresses
                    $.each(response.emailContacts, function(i, email) {
                        content = $("<div class=\"contentbox\">").append(
                            $("<div class=\"contenttype\">").append(
                                $("<h2>").text(email)
                            ),
                            $("<br class=\"clear_both\">")
                        );
        
                        // space the displayed students between 2 columns
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
    	
        // slide out classmate controls
        $("#classmateControls").slideUp("slow", function() {
            $("#contentLeft").slideDown("slow");
            $("#contentRight").slideDown("slow");
        });
    });
});