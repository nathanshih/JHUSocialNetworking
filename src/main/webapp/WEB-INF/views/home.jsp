<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>

<head>
	<title>JHU Social Networking</title>
	<meta content="noindex, nofollow" name="robots">
	<link href="${pageContext.request.contextPath}/resources/style/home.css" rel="stylesheet" type="text/css" media="screen" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>	
	<script src="${pageContext.request.contextPath}/resources/js/home.js"></script>
</head>

<body>
	<div id="main">
	
		<!-- Create Div First For Login Form -->
		<div id="first">
			<form action="" method="post">
				<h3>Welcome to JHU's social networking site!</h3>
				<img id="divider" src="${pageContext.request.contextPath}/resources/images/jhulogo.jpg">
				<input id="loginemail" placeholder="Email" type="text">
				<input id="loginpassword" placeholder="Password" type="password">
				<input id="login" type="button" value="Sign In">
				<p id="one">
					<a href="#">Forgot Password ?</a>
				</p>
				<p id="two">
					Don't have account? <a class="signup" id="signup" href="#">Signup here</a>
				</p>
			</form>
		</div>
		
		<!-- Create Div Second For Signup Form-->
		<div id="second">
			<form action="" id="form" method="post" name="form">
				<h3>Create your Free Account</h3>
				<img id="divider" src="${pageContext.request.contextPath}/resources/images/jhulogo.jpg">
				<input id="name" placeholder="Full Name" type="text">
				<input id="registeremail" placeholder="Email" type="text">
				<input id="registerpassword" placeholder="Password" type="password">
				<input id="contact" placeholder="Contact Number" type="text">
				<input id="register" type="button" value="Create your account.">
				<p id="two">
					Already have an account? <a class="signin" id="signin" href="#">Sign in</a>
				</p>
			</form>
		</div>
		
	</div>
</body>

</html>
