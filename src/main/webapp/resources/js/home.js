$(document).ready(function() {
	
	//	On Click SignIn Button Checks For Valid E-mail And All Field Should Be Filled
	$("#login").click(function() {
		var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
		if ($("#loginemail").val() === '' || $("#loginpassword").val() === '') {
			alert("All fields required.");
		} else if (!($("#loginemail").val()).match(email)) {
			alert("Invalid email entered.");
		} else {
			alert("You have successfully logged in.");
			$("form")[0].reset();
		}
	});
	
	$("#register").click(function() {
		var email = new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);
		if ($("#name").val() === '' || $("#registeremail").val() === '' || $("#registerpassword").val() === '' || $("#contact").val() === '') {
			alert("All fields required.");
		} else if (!($("#registeremail").val()).match(email)) {
			alert("Invalid email entered.");
		} else {
			alert("You have successfully signed up. Now you can login.");
			$.ajax({
				contentType: "application/json",
				data: JSON.stringify({
					"name": $("#name").val(),
					"email": $("#registeremail").val(),
					"password": $("#registerpassword").val(),
					"contact": $("#contact").val()
				}),
				dataType: "json",
				type: "POST"
			});
			
			$("#form")[0].reset();
			$("#second").slideUp("slow", function() {
				$("#first").slideDown("slow");
			});
		}
	});
	
	//	On Click SignUp It Will Hide Login Form and Display Registration Form
	$("#signup").click(function() {
		$("#first").slideUp("slow", function() {
			$("#second").slideDown("slow");
		});
	});
	
	//	On Click SignIn It Will Hide Registration Form and Display Login Form
	$("#signin").click(function() {
		$("#second").slideUp("slow", function() {
			$("#first").slideDown("slow");
		});
	});
});