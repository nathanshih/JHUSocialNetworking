$(document).ready(function() {
	
	//	login
	$("#login").click(function() {
		
		// first check that the email is in correct format and all fields are filled in
		var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
		if ($("#loginemail").val() === '' || $("#loginpassword").val() === '') {
			alert("All fields required.");
		} else if (!($("#loginemail").val()).match(email)) {
			alert("Invalid email entered.");
		} else {
			// TODO: implement login jquery logic
			alert("You have successfully logged in.");
			document.cookie = "studentId=1;";
			window.location = "main";
		}
	});
	
	// register
	$("#register").click(function() {
		
		// first check that the email is in correct format and all fields are filled in
		var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
		if ($("#name").val() === '' || $("#registeremail").val() === '' || $("#registerpassword").val() === '' || $("#contact").val() === '') {
			alert("All fields required.");
		} else if (!($("#registeremail").val()).match(email)) {
			alert("Invalid email entered.");
		} else {
			alert("You have successfully signed up. Now you can login.");
			
			// send the register call
			$.ajax({
				url: "register",
				contentType: "application/json",
				data: JSON.stringify({
					"name": $("#name").val(),
					"email": $("#registeremail").val(),
					"password": $("#registerpassword").val(),
					"contact": $("#contact").val()
				}),
				dataType: "json",
				type: "POST",
				success: function() {
				    $("form")[0].reset();
				}
			});
			
			$("#form")[0].reset();
			$("#second").slideUp("slow", function() {
				$("#first").slideDown("slow");
			});
		}
	});
	
	//	hide login form and display registration form
	$("#signup").click(function() {
		$("#first").slideUp("slow", function() {
			$("#second").slideDown("slow");
		});
	});
	
	//	hide registration form and display login form
	$("#signin").click(function() {
		$("#second").slideUp("slow", function() {
			$("#first").slideDown("slow");
		});
	});
});
