<%@page import="com.sunrays.javarefbook.model.ModuleModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Project List</h2>
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
		<form action="ProjectListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>Id<br> <input type="checkbox"></th>
					<th>Name</th>
					<th>Client</th>
					<th>Description</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Manager</th>
					<th>Edit</th>
				<tr>

					<%
						List projectList = (List) request.getAttribute("projectList");
						if (projectList != null) {
							Iterator iterator = projectList.iterator();
							while (iterator.hasNext()) {
								ProjectModel projectModel = (ProjectModel) iterator.next();
					%>
				
				<tr>
					<td><input type="checkbox" value="<%=projectModel.getId()%>"
						name="ids"></td>
					<td><%=projectModel.getName()%></td>
					<td><%=projectModel.getClient()%></td>
					<td><%=projectModel.getDescription()%></td>
					<td><%=projectModel.getStartdate()%></td>
					<td><%=projectModel.getEnddate()%></td>
					<td><%=projectModel.getManager()%></td>
					<td><a
						href="ProjectCtl.do?id=<%=projectModel.getId()%>&operation=Get">Edit</a></td>
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