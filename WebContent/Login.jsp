<html>
<body>
	<center>
		<%
			String message = (String) request.getAttribute("message");
			if (message != null) {
		%>
		<%=message%>
		<%
			}
		%>
		<form action="LoginCtl" method="post">
			<table>
				<tr>
					<td>Username</td>
					<td><input type="text" name="userName" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="userPassword" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="operation" value="Login"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>