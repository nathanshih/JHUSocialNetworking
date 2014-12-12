<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Use JSTL Tags --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <%-- Include the header --%>
    <%@ include file="../includes/header.html"%>
    <script src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
</head>

<body>
	<div id="main" class="container">
        <%-- Include the banner --%>
        <%@ include file="../includes/banner.html"%>
		<div id="contentArea" class="contentArea">
            <div id="leftNavigation" class="leftnavigation">
                <%-- Include the navigation menu --%>
                <%@ include file="../includes/navigation.html"%>
                <a id="admin" href="admin" >Admin</a>
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
            <%-- Include the content --%>
            <%@ include file="../includes/content.html"%>
		</div>
	</div>
    <%-- Include the footer --%>
    <%@ include file="../includes/footer.html"%>
</body>

</html>
