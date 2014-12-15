// Activated when clicking "Cart" from left navigation panel
$(document).ready(function() {

    // clear contentLeft & contentRight
    $("#contentLeft").empty();
    $("#contentRight").empty();

    // AJAX request for cart data
    $.ajax({
        url: "cart",
        contentType: "application/json",
        dataType: "json",
        type: "GET",
        error: function() {
            alert("Unable to display cart.");
        },
        success: function(response) {
            var counter = 1;
            var content;

            // iterate through cart and display
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

                counter = counter + 1;
            });
        }
    });
});