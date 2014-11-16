<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Example page</title>
	<link href="${pageContext.request.contextPath}/resources/style/example.css" rel="stylesheet" type="text/css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/example.js"></script>
</head>

<body>
	<input id="getAllStudents" name="getAllStudents" type="submit" value="Get All Students">
	
	<br>
	
	<table id="students">
		<tr>
			<th>Student ID</th>
			<th>Name</th>
			<th>Email</th>
		<tr>
	</table>
	
	<br>
	
	<input id="echo1" name="echo1" type="submit" value="Copy cat">
	<input id="echo1text" name="echo1text" type="text" value="Enter some text here.">
	<br>
	<p id="response1">
</body>

</html>