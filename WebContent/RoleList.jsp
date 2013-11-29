<%@page import="com.sunrays.javarefbook.model.RoleModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>User List</h2>

		<form action="TimesheetCtl.do" method="post">
			<table border="1">
				<tr>
					<th>ID</th>
					<th>Role</th>
					<th>Edit</th>
				</tr>
				<%
					List roleList = (List) request.getAttribute("roleList");
					if (roleList != null) {
						Iterator iterator = roleList.iterator();
						while (iterator.hasNext()) {
							RoleModel roleModel = (RoleModel) iterator.next();
				%>

				<tr>
					<td><%=roleModel.getId()%></td>
					<td><%=roleModel.getName()%></td>
					<td><a href="#">Edit</a></td>
				</tr>
				<%
					}
					}
				%>
				<tr>
					<td></td>
					<td><input name="operation" value="Delete" type="submit"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>