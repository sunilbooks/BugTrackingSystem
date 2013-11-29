<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.ModuleModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>User List</h2>
		<br>
		<%
			String message = (String) request.getAttribute("message");
			String errorMessage = (String) request.getAttribute("errorMessage");

			if (message != null) {
		%>
		<b><%=message%></b>
		<%
			}
			if (errorMessage != null) {
		%>
		<font color="red"><b><%=errorMessage%></b></font>
		<%
			}
		%>
		<br> <br>
		<form action="UserListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>Id<br> <input type="checkbox"></th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Address</th>
					<th>Gender</th>
					<th>Telephone No.</th>
					<th>Role</th>
					<th>Date Of Birth</th>
					<th>User Name</th>
					<th>User Password</th>
					<th>Organization</th>
					<th>Edit</th>
				</tr>
				<%
					List userList = (List) request.getAttribute("userList");
					if (userList != null) {
						Iterator iterator = userList.iterator();
						while (iterator.hasNext()) {
							UserModel userModel = (UserModel) iterator.next();
				%>

				<tr>
					<td><input type="checkbox" value="<%=userModel.getId()%>"
						name="ids"></td>
					<td><%=userModel.getFirstName()%></td>
					<td><%=userModel.getLastName()%></td>
					<td><%=userModel.getAddress()%></td>
					<td><%=userModel.getGender()%></td>
					<td><%=userModel.getTelephonNo()%></td>
					<td><%=userModel.getRoleId()%></td>
					<td><%=userModel.getDateOfBirth()%></td>
					<td><%=userModel.getUserName()%></td>
					<td><%=userModel.getUserPassword()%></td>
					<td><%=userModel.getOrgName()%></td>
					<td><a
						href="UserCtl.do?operation=Get&id=<%=userModel.getId()%>">Edit</a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
			<br> <input name="operation" value="Delete" type="submit">
		</form>
	</center>
</body>
</html>