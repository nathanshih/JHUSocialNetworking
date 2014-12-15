<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Use JSTL Tags --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <%-- Include the header --%>
    <%@ include file="../includes/header.html"%>
    <script src="${pageContext.request.contextPath}/resources/js/courses.js"></script>
</head>

<body>
	<div id="main" class="container">
        <%-- Include the banner --%>
        <%@ include file="../includes/banner.html"%>
		<div id="contentArea" class="contentArea">
            <div id="leftNavigation" class="leftnavigation">
                <%-- Include the navigation menu --%>
                <%@ include file="../includes/navigation.html"%>
            </div>
            <div id="courseControls">
                <form action="" method="post">
                    <select id="courseSelect" name="courseSelect" required>
                        <option value="" selected disabled>Select courses to view...</option>
                        <option value="allCourses">All Courses</option>
                        <option value="completedCourses">Completed Courses</option>
                    </select>
                    <input id="courseButton" name="courseButton" class="submitButton" 
                           type="button" value="Display Courses">
                </form>
            </div>
            <%-- Include the content --%>
            <%@ include file="../includes/content.html"%>
		</div>
	</div>
    <%-- Include the footer --%>
    <%@ include file="../includes/footer.html"%>
</body>

</html>
