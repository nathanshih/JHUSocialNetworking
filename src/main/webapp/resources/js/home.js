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
			// do the login
			loginEmail = $("#loginemail").val();
			loginPassword = $("#loginpassword").val();
			$.ajax({
				url: "login?email=" + loginEmail + "&password=" + loginPassword,
				contentType: "text/plain",
				type: "POST",
				success: function() {
					alert("You have successfully logged in.");
		            document.cookie = "studentId=1;";
					window.location = "main";
				},
				error: function() {
					alert("Email or password not found.");
					$("form")[0].reset();
				}
			});		
		}
	});
	
	// register
	$("#register").click(function() {
		
		// first check that the email is in correct format and all fields are filled in
		var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
		if ($("#name").val() === '' || $("#registeremail").val() === '' || $("#registerpassword").val() === '' || $("#discipline").val() === '') {
			alert("All fields required.");
		} else if (!($("#registeremail").val()).match(email)) {
			alert("Invalid email entered.");
		} else {		
			// send the register call
			$.ajax({
				url: "register",
				contentType: "application/json",
				data: JSON.stringify({
					"name": $("#name").val(),
					"email": $("#registeremail").val(),
					"password": $("#registerpassword").val(),
					"discipline": $("#discipline").val()
				}),
				dataType: "json",
				type: "POST",
				success: function() {
					alert("You have successfully signed up.");
					window.location = "main";
				},
				error: function() {
					alert("Registration failed.");
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
