$(document).ready(function() {
	
	$("#getAllStudents").click(function() {
		$.ajax({
			url: "students",
			contentType: "application/json",
			dataType: "json",
			type: "GET",
			success: function(response) {
				$.each(response, function(i, student) {
					$("<tr>").append(
					$("<td>").text(student.studentId),
					$("<td>").text(student.name),
					$("<td>").text(student.email)).appendTo("#students");
				});
			}
		});
	});
	
	$("#echo1").click(function() {
		var echoText = $("#echo1text").val();
		$("#response1").text(echoText);
	});
});