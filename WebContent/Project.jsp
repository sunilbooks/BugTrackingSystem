<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.ClientModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<h2>Project Form</h2>
	<br>
	<%
		String manager = "";
		String clientName = "";
		List userList = (List) request.getAttribute("userList");
		List clientList = (List) request.getAttribute("clientList");
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
	<%
		ProjectModel projectModel = (ProjectModel) request
				.getAttribute("projectModel");
		if (projectModel == null) {
			projectModel = new ProjectModel();
			projectModel.setId(null);
			projectModel.setName("");
			projectModel.setClient("");
			projectModel.setStartdate(null);
			projectModel.setEnddate(null);
			projectModel.setDescription("");
			projectModel.setManager("");
		} else {
			clientName = projectModel.getClient();
			manager = projectModel.getManager();
		}
	%>
	<form action="ProjectCtl.do" method="post">
		<table>
			<input type="hidden" name="id" value="<%=projectModel.getId()%>">
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name"
					value="<%=projectModel.getName()%>"></td>
			</tr>
			<tr>
				<th>Client:</th>
				<td><select name="client">
						<option value="">--Select--</option>
						<%
							if (!clientName.equals("")) {
						%>
						<option value="<%=clientName%>" selected="selected"><%=clientName%></option>
						<%
							}
							if (clientList != null) {
								Iterator iterator = clientList.iterator();

								while (iterator.hasNext()) {
									ClientModel clientModel = (ClientModel) iterator.next();
									if (!clientName.equals(clientModel.getName())) {
						%>
						<option value="<%=clientModel.getName()%>"><%=clientModel.getName()%></option>
						<%
							}
								}
							}
						%>
				</select></td>
			</tr>
			<tr>
				<th>Description:</th>
				<td><textarea name="description" rows="3" cols="40"><%=projectModel.getDescription()%></textarea></td>
			</tr>
			<tr>
				<th>Start Date:</th>
				<td></td>
			</tr>
			<tr>
				<th>Last Date:</th>
				<td></td>
			</tr>
			<tr>
				<th>Manager:</th>
				<td><select name="manager">
						<option value="">--Select--</option>
						<%
							if (!manager.equals("")) {
						%>
						<option value="<%=manager%>" selected="selected"><%=manager%></option>
						<%
							}
							if (userList != null) {
								Iterator iterator = userList.iterator();

								while (iterator.hasNext()) {
									UserModel userModel = (UserModel) iterator.next();

									if (!manager.equals(userModel.getFirstName() + " "
											+ userModel.getLastName())) {
						%>
						<option
							value="<%=userModel.getFirstName()%>&nbsp;<%=userModel.getLastName()%>"><%=userModel.getFirstName()%>&nbsp;<%=userModel.getLastName()%></option>
						<%
							}
								}
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="operation" value="Save"></td>
			</tr>
		</table>
	</form>
</body>
</html>