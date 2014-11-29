$(document).ready(function() {

    $("#profile").click(function() {
        $("#contentLeft").slideUp("slow", function() {
            $("#profileForm").slideDown("slow");
        });
    });

	$("#update").click(function() {
		var name = $("#name").val();
		$("#stuff").text(name);
	});

	$("#courses").click(function() {
		$.ajax({
			url: "courses",
			contentType: "application/json",
			dataType: "json",
			type: "GET",
			success: function(response) {
				$.each(response, function(i, course) {
					$("<div class=\"contentbox\">").append(
							$("<div class=\"contenttype\">").append(
									$("<h2>").text(course.name),
									$("<p>").text(course.classId),
									$("<div id=\"CollapsiblePanel\"" + i + " class=\"CollapsiblePanel\">").append(
											$("<div class=\"CollapsiblePanelTab\">").append(
													$("<a href=\"#\">").text("MORE INFORMATION")
											),
											$("<div class=\"CollapsiblePanelContent\">").append(
													$("<p>").text(course.professor),
													$("<p>").text(course.url)
											)
									)
							)
					).appendTo("#contentLeft");
				});
			}
		});
	});
	
	$("#cart").click(function() {
		// need to clear contentLeft
		$("<div class=\"contentbox\">").append(
				$("<div class=\"contenttype\">").append(
						$("<h2>").text("Foundations of Computer Architecture"),
						$("<p>").text("605.411"),
						$("<div id=\"CollapsiblePanel1\"" + " class=\"CollapsiblePanel\">").append(
								$("<div class=\"CollapsiblePanelTab\">").append(
										$("<a href=\"#\">").text("MORE INFORMATION")
								),
								$("<div class=\"CollapsiblePanelContent\">").append(
										$("<p>").text("Horace Malcom"),
										$("<p>").text("http://apps.ep.jhu.edu/course-homepages/2814-605.411-foundations-of-computer-architecture-malcom")
								)
						)
				),
				$("<br class=\"clear_both\">")
		)
		.appendTo("#contentLeft");
	});
	
	$("#classmates").click(function() {
		$.ajax({
			url: "students",
			contentType: "application/json",
			dataType: "json",
			type: "GET",
			success: function(response) {
				$.each(response, function(i, student) {
					$("<div class=\"contentbox\">").append(
							$("<div class=\"contenttype\">").append(
									$("<h2>").text(student.name),
									$("<p>").text(student.email)
//									,$("<div id=\"CollapsiblePanel\"" + i + " class=\"CollapsiblePanel\">").append(
//											$())
							)
					).appendTo("#contentLeft");
				});
			}
		});
	});
});