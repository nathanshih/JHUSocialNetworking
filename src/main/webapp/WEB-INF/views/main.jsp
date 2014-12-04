<!DOCTYPE html>
<html>

<head>
    <title>JERCS Dashboard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="${pageContext.request.contextPath}/resources/style/dashboard.css" 
          rel="stylesheet" type="text/css" media="screen"/>
    <link href="${pageContext.request.contextPath}/resources/style/SpryCollapsiblePanel.css" 
          rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/resources/js/SpryCollapsiblePanel.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>    
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>

<body>
	<div id="main" class="container">
		<div id="bannerArea" class="bannerArea">
			<div id="topLogo" class="toplogo">
			<a href="#">
			<img src="${pageContext.request.contextPath}/resources/images/banner_logo.png" 
			border="0" /></a>
			</div>
		</div>
		<div id="contentArea" class="contentArea">
			<div id="leftNavigation" class="leftnavigation">
				<a id="home" href="#" >Home</a>
				<a id="courses" href="#" >Courses</a>
				<a id="cart" href="#" >Cart</a>
				<a id="classmates" href="#" >Classmates</a>
                <a id="profile" href="#" >Profile</a>
                <a id="admin" href="#" >Admin</a>
			</div>
            <div id="courseControls">
                <form action="" method="post">
                    <select id="courseSelect" name="courseSelect" required>
                        <option value="" selected disabled>Select courses to view...</option>
                        <option value="allCourses">All Courses</option>
                        <option value="checkedOutCourses" disabled>Checked Out Courses</option>
                        <option value="completedCourses" disabled>Completed Courses</option>
                    </select>
                    <input id="courseButton" name="courseButton" type="button" value="Display Courses">
                </form>
            </div>
            <div id="profileForm">
                <form action="" id="form" method="post">
                    <h3>User Profile Information</h3>
                    <table>
                        <tr>
                            <td>Name:</td>
                            <td>
                                <input id="name" name="name" type="text" required>
                            </td>
                        </tr>
                        <tr>
                            <td>Email Address:</td>
                            <td>
                                <input id="email" name="email" type="email" required>
                            </td>
                        </tr>
                        <tr>
                            <td>Degree Program:</td>
                            <td>
                                <input id="degree" name="degree" type="text" required>
                            </td>
                        </tr>
                        <tr>
                            <td>Number of courses completed:</td>
                        </tr>
                        <tr>
                            <td>Number of courses checked out:</td>
                        </tr>
                    </table>
                    <input id="updateProfile" name="updateProfile" type="button" value="Update Profile">
                </form>
            </div>
            <div id="adminForm">
                <form action="" method="post">
                    <h3>Administrator Access</h3>
                    <table>
                        <tr>
                            <td>Student Name:</td>
                            <td>
                                <input id="studentName" name="studentName" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>Student Email Address:</td>
                            <td>
                                <input id="studentEmail" name="studentEmail" type="email">
                            </td>
                        </tr>
                        <tr>
                            <td>Student Degree Program:</td>
                            <td>
                                <input id="studentDegree" name="studentDegree" type="text">
                            </td>
                        </tr>
                    </table>
                    <input id="addStudent" name="addStudent" type="button" value="Add Student">
                    <table>
                        <tr>
                            <td>Course Name:</td>
                            <td>
                                <input id="courseName" name="courseName" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>Course ID:</td>
                            <td>
                                <input id="courseId" name="courseId" type="text">
                            </td>
                        </tr>
                        <tr>
                            <td>Course Description:</td>
                            <td>
                                <input id="courseDescription" name="courseDescription" type="text">
                            </td>
                        </tr>
                    </table>
                    <input id="addCourse" name="addCourse" type="button" value="Add Course">
                </form>
            </div>
			<div id="contentLeft" class="contentleft">
			</div>
			<div id="contentRight" class="contentright">
			</div>
			<br class="clear_both" />
		</div>
	</div>
	<script id="javascript" type="text/javascript">
	</script>
	<div id="footerArea" class="footerArea">
		<div id="footerContent" class="footercontent">
			<div id="footerNav" class="footernav"><a href="#" >Privacy Policy</a>  &bull;  <a href="#" >Contact Us</a> </div>
			<div id="copyright" class="copyright">&copy; 2014 JERCS.  All rights reserved.</div>
		</div>
	</div>

</body>

</html>
