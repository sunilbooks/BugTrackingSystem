<%@page import="com.sunrays.javarefbook.model.ModuleModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Module List</h2>
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
		<br>
		<br>
		<form action="ModuleListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>Id<br> <input type="checkbox"></th>
					<th>Name</th>
					<th>Description</th>
					<th>Project Name</th>
					<th>Edit</th>
				<tr>
					<%
						List moduleList = (List) request.getAttribute("moduleList");
						if (moduleList != null) {
							Iterator iterator = moduleList.iterator();
							while (iterator.hasNext()) {
								ModuleModel moduleModel = (ModuleModel) iterator.next();
					%>
				
				<tr>
					<td><input type="checkbox" value="<%=moduleModel.getId()%>"
						name="ids"></td>
					<td><%=moduleModel.getName()%></td>
					<td><%=moduleModel.getDescription()%></td>
					<td><%=moduleModel.getProjectName()%></td>
					<td><a
						href="ModuleCtl.do?operation=Get&id=<%=moduleModel.getId()%>">Edit</a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
			<input type="submit" value="Delete" name="operation">
		</form>
	</center>
</body>
</html>