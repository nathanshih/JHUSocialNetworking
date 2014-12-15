<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Use JSTL Tags --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <%-- Include the header --%>
    <%@ include file="../includes/header.html"%>
    <script src="${pageContext.request.contextPath}/resources/js/classmates.js"></script>
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
            <div id="classmateControls">
                <form action="" method="post">
                    <select id="classmateSelect" name="classmateSelect" required>
                        <option value="" selected disabled>Select classmates to view...</option>
                        <option value="getAllContacts">Contacts</option>
                        <option value="students">All Students</option>
                    </select>
                    <input id="classmateButton" name="classmateButton" class="submitButton" 
                           type="button" value="Display Classmates">
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
