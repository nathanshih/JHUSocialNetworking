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
            <%-- Include the content --%>
            <%@ include file="../includes/content.html"%>
		</div>
	</div>
    <%-- Include the footer --%>
    <%@ include file="../includes/footer.html"%>
</body>

</html>
