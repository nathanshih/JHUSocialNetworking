<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Use JSTL Tags --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <%-- Include the header --%>
    <%@ include file="../includes/header.html"%>
</head>

<body>
	<div id="main" class="container">
        <%-- Include the banner --%>
        <%@ include file="../includes/banner.html"%>
		<div id="contentArea" class="contentArea">
            <%-- Include the navigation menu --%>
            <%@ include file="../includes/navigation.html"%>
			<div id="contentLeft" class="contentleft">
			</div>
			<div id="contentRight" class="contentright">
			</div>
			<br class="clear_both" />
		</div>
	</div>
	<script id="javascript" type="text/javascript">
	</script>
    <%-- Include the footer --%>
    <%@ include file="../includes/footer.html"%>
</body>

</html>
