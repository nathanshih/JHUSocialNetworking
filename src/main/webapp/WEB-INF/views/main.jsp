<!DOCTYPE html>
<html>

<head>
    <title>JERCS Dashboard</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link href="${pageContext.request.contextPath}/resources/style/dashboard.css" 
          rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/resources/style/SpryCollapsiblePanel.css" 
          rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/resources/js/SpryCollapsiblePanel.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>    
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
				<a href="#" >Home</a>
				<a id="courses" href="#" >Courses</a>
				<a id="cart" href="#" >Cart</a>
				<a id="classmates" href="#" >Classmates</a>
                <a id="profile" href="#" >Profile</a>
			</div>
            <div id="controls">
                <form action="" method="post">
                    <select name="courseSelect" size="4" multiple required>
                        <option value="all">All Courses</option>
                        <option value="checkedOut">Checked Out Courses</option>
                        <option value="completed">Completed Courses</option>
                        <option value="search">Search for Courses...</option>
                    </select>
                    <br />
                    <input id="searchText" name="searchText" type="text">
                    <br />
                    <input id="searchButton" name="searchButton" type="button">
                </form>
            </div>
            <div id="profileForm">
                <form action="" method="post">
                    <input id="name" name="name" type="text">
                    <input id="email" name="email" type="text">
                    <input id="update" name="update" type="button" value="Update">
                </form>
            </div>
			<div id="contentLeft" class="contentleft">
			</div>
			<div id="contentRight" class="contentright">
			<p id="stuff">
			</div>
			<br class="clear_both" />
		</div>
	</div>
	<script type="text/javascript">
        var cp1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel1", { contentIsOpen: false });
//        var cp1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel2", { contentIsOpen: false });
//        var cp1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel3", { contentIsOpen: false });
//        var cp1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel4", { contentIsOpen: false });
//        var cp1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel5", { contentIsOpen: false });
//        var cp1 = new Spry.Widget.CollapsiblePanel("CollapsiblePanel6", { contentIsOpen: false });
	</script>
	<div id="footerArea" class="footerArea">
		<div id="footerContent" class="footercontent">
			<div id="footerNav" class="footernav"><a href="#" >Privacy Policy</a>  &bull;  <a href="#" >Contact Us</a> </div>
			<div id="copyright" class="copyright">&copy; 2014 JERCS.  All rights reserved.</div>
		</div>
	</div>

</body>

</html>
