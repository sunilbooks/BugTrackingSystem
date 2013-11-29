<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BTS</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="utils/zapatec.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="reset.css" />
<link rel="stylesheet" type="text/css" href="style.css" />
<link rel="stylesheet" type="text/css" href="./css/pmbm.css" />

<!-- basic Javascript file for the tree-->
<script src="zptree/src/tree.js" type="text/javascript"></script>

</head>
<body>
	<div id="wrap">
		<div id="header">
			<ul id="nav">
				<%!UserModel userModel = null;
	String body = null;%>
				<%
					userModel = (UserModel) session.getAttribute("user");
					if (userModel != null) {
				%>

				<li><font color="#ffffff"> Welcome:&nbsp;<%=userModel.getFirstName()%>&nbsp;<%=userModel.getLastName()%>
				</font></li>
				<%
					} else {
				%>
				<li><font color="#ffffff">Welcome</font></li>
				<%
					}
				%>
				<li><a href="MyProfileCtl.do">MyProfile</a></li>
				<li><a href="http://www.sunrays.co.in" class="style1">Home</a>
				</li>
				<li><a href="http://www.sunrays.co.in" class="style1">Contact
						us</a></li>

			</ul>

			<h1 id="logo">
				BTS <span>Bug Tracking System</span>
			</h1>
		</div>
		<div id="content">

			<div id="left">
				<%
					if (userModel != null) {
						body = null;
				%>
				<jsp:include page="Menu.jsp"></jsp:include>
				<%
					} else {
				%>
				<jsp:include page="Login.jsp"></jsp:include>
				<%
					}
				%>
			</div>
			<div id="right">
				<%
					body = request.getParameter("body");
					if (body == null) {
						body = "Welcome.jsp";
					}
				%>
				<jsp:include page="<%=body%>"></jsp:include>
			</div>
		</div>
		<div id="footer">
			<div id="ftlink">
				<a href="#">Home</a> | <a href="#">About Us</a> | <a href="#">Production</a>
				| <a href="#">Submission</a> | <a href="#">Contact</a>
			</div>
			<div id="copyright">
				Copyright &copy; Sunil Sahu 2013-14 <a
					href="http://home.sunrays.co.in" target="_blank">Visit: Sunrays</a>
			</div>
		</div>
	</div>
</body>
</html>