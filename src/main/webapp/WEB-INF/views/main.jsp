<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<%-- Use JSTL Tags --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

<head>
    <%-- Include the header --%>
    <%@ include file="../includes/header.html"%>
    <script src="${pageContext.request.contextPath}/resources/js/main.js"></script>
</head>

<body>
	<div id="main" class="container">
        <%-- Include the banner --%>
        <%@ include file="../includes/banner.html"%>
		<div id="contentArea" class="contentArea">
            <div id="leftNavigation" class="leftnavigation">
                <%-- Include the navigation menu --%>
                <%@ include file="../includes/navigation.html"%>
                <a id="profile" href="#" >Profile</a>
                <a id="admin" href="admin" >Admin</a>
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
