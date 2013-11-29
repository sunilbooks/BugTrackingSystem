<%@page import="com.sunrays.javarefbook.model.ModuleModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.ClientModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<h2>Module Form</h2>
	<br>
	<%
		List projectList = (List) request.getAttribute("projectList");
		String message = (String) request.getAttribute("message");
		String errorMessage = (String) request.getAttribute("errorMessage");
		String projectName = "";
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

		ModuleModel moduleModel = (ModuleModel) request
				.getAttribute("moduleModel");
		if (moduleModel == null) {
			moduleModel = new ModuleModel();
			moduleModel.setId(null);
			moduleModel.setName("");
			moduleModel.setDescription("");
			moduleModel.setProjectName("");
		} else {
			projectName = moduleModel.getProjectName();
		}
	%>
	<br>
	<br>

	<form action="ModuleCtl.do" method="post">
		<table>
			<input type="hidden" name="id" value="<%=moduleModel.getId()%>">
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name"
					value="<%=moduleModel.getName()%>"></td>
			</tr>
			<tr>
				<th>Description:</th>
				<td><textarea name="description" rows="3" cols="40"><%=moduleModel.getDescription()%></textarea></td>
			</tr>
			<tr>
				<th>Project:</th>
				<td><select name="projectName">
						<option value="">--Select--</option>
						<%
							if (!projectName.equals("")) {
						%>
						<option value="<%=projectName%>" selected="selected"><%=projectName%></option>
						<%
							}
						%>
						<%
							if (projectList != null) {
								Iterator iterator = projectList.iterator();

								while (iterator.hasNext()) {
									ProjectModel projectModel = (ProjectModel) iterator.next();
									if (!projectName.equals(projectModel.getName())) {
						%>
						<option value="<%=projectModel.getName()%>"><%=projectModel.getName()%></option>
						<%
							}
								}
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<%
					if (moduleModel.getId() != null) {
				%>
				<td><input type="submit" name="operation" value="Update"></td>
				<%
					} else {
				%>
				<td><input type="submit" name="operation" value="Save"></td>
				<%
					}
				%>
			</tr>
		</table>
	</form>
</body>
</html>