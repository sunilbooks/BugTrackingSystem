<%@page import="com.sunrays.javarefbook.model.BugModel"%>
<%@page import="com.sunrays.javarefbook.model.ModuleModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<p class="title">Bug Form</p>
		<br>
		<%
			String message = (String) request.getAttribute("message");
			String errorMessage = (String) request.getAttribute("errorMessage");
			String projectName = "";
			String moduleName = "";
			String assigned = "";

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
		<%
			UserModel userModel = (UserModel) session.getAttribute("user");

			BugModel bugModel = (BugModel) request.getAttribute("bugModel");
			if (bugModel == null) {
				bugModel = new BugModel();
				bugModel.setId(null);
				bugModel.setType(null);
				bugModel.setProject(null);
				bugModel.setModule(null);
				bugModel.setTitle(null);
				bugModel.setPriority(null);
				bugModel.setSeverity(null);
				bugModel.setDescription(null);
				bugModel.setOwner(null);
				bugModel.setStatus(null);
				bugModel.setIdentifierId(null);
				bugModel.setCurrent_date(null);
				bugModel.setTargetDate(null);
				bugModel.setResolution(null);
				bugModel.setIdentifierInBuild(null);
				bugModel.setFixInBuild(null);
			} else {
				projectName = bugModel.getProject();
				moduleName = bugModel.getModule();
				assigned = userModel.getFirstName() + " "
						+ userModel.getLastName();
			}
		%>

		<%
			List projectList = (List) request.getAttribute("projectList");
			List moduleList = (List) request.getAttribute("moduleList");
			List userList = (List) request.getAttribute("userList");

			Iterator iterator = null;
		%>

		<form action="UserCtl.do" method="post">
			<table>
				<input type="hidden" name="id">
				<tr>
					<th>Type:</th>
					<td><select name="type">
							<option value="">--Select--</option>
							<%
								if (bugModel.getType() != null) {
							%>
							<option value="<%=bugModel.getType()%>" selected="selected"><%=bugModel.getType()%></option>
							<%
								}

								String selArr[] = { "Task", "Bug" };

								for (String val : selArr) {
									if (!bugModel.getType().equals(val)) {
							%>
							<option value="<%=val%>"><%=val%></option>
							<%
								}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>Project:</th>
					<td><select name="project">
							<option value="">--Select--</option>
							<%
								if (!projectName.equals("")) {
							%>
							<option value="<%=projectName%>" selected="selected"><%=projectName%></option>
							<%
								}

								if (projectList != null) {
									iterator = projectList.iterator();
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
					<th>Module:</th>
					<td><select name="module">
							<option value="">--Select--</option>
							<%
								if (!moduleName.equals("")) {
							%>
							<option value="<%=moduleName%>" selected="selected"><%=moduleName%></option>
							<%
								}
								if (moduleList != null) {
									iterator = moduleList.iterator();
									while (iterator.hasNext()) {
										ModuleModel moduleModel = (ModuleModel) iterator.next();
										if (!moduleName.equals(moduleModel.getName())) {
							%>
							<option value="<%=moduleModel.getName()%>"><%=moduleModel.getName()%></option>
							<%
								}
									}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>Title:</th>
					<td><input type="text" name="title"
						value="<%=bugModel.getTitle()%>"></td>
				</tr>
				<tr>
					<th>Severity:</th>
					<td><select name="severity">
							<option value="">--Select--</option>
							<%
								if (bugModel.getSeverity() != null) {
							%>
							<option value="<%=bugModel.getSeverity()%>" selected="selected"><%=bugModel.getSeverity()%></option>
							<%
								}

								String sevArr[] = { "Simple", "Medium", "Critical" };

								for (String val : sevArr) {
									if (!bugModel.getSeverity().equals(val)) {
							%>
							<option value="<%=val%>"><%=val%></option>
							<%
								}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>Priority:</th>
					<td><select name="priority">
							<option value="">--Select--</option>
							<%
								if (bugModel.getPriority() != null) {
							%>
							<option value="<%=bugModel.getPriority()%>" selected="selected"><%=bugModel.getPriority()%></option>
							<%
								}

								String pArr[] = { "Hign", "Medium", "Low" };

								for (String val : pArr) {
									if (!bugModel.getPriority().equals(val)) {
							%>
							<option value="<%=val%>"><%=val%></option>
							<%
								}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>Identifier in Build:</th>
					<td><input type="text" name="identifierInBuild"
						value="<%=bugModel.getIdentifierInBuild()%>"></td>
				</tr>
				<tr>
					<th>Description:</th>
					<td><textarea name="description" rows="3" cols="40"><%=bugModel.getDescription()%></textarea></td>
				</tr>
				<!-- File -->
				<tr>
					<th>Assigned To:</th>
					<td><select name="owner">
							<option value="">--Select--</option>
							<%
								if (!assigned.equals("")) {
							%>
							<option value="<%=assigned%>" selected="selected"><%=assigned%></option>
							<%
								}
								if (userList != null) {
									iterator = userList.iterator();
									while (iterator.hasNext()) {
										UserModel userMo = (UserModel) iterator.next();
										if (!assigned.equals(userMo.getFirstName() + " "
												+ userMo.getLastName())) {
							%>
							<option
								value="<%=userMo.getFirstName()%>&nbsp;<%=userMo.getLastName()%>"><%=userMo.getFirstName()%>&nbsp;<%=userMo.getLastName()%></option>
							<%
								}
									}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>Status:</th>
					<td><select name="status">
							<option value="">--Select--</option>
							<%
								if (bugModel.getStatus() != null) {
							%>
							<option value="<%=bugModel.getStatus()%>" selected="selected"><%=bugModel.getStatus()%></option>
							<%
								}
								String stArr[] = { "Open", "Fixed", "Hold", "Close", "Rejected",
										"Reopen" };
								for (String val : stArr) {
									if (!bugModel.getStatus().equals(val)) {
							%>
							<option value="<%=val%>"><%=val%></option>
							<%
								}
								}
							%>
					</select></td>
				</tr>
				<!-- date:-targetDate -->
				<tr>
					<th>Fix in Build:</th>
					<td><input type="text" name="fixInBuild"
						value="<%=bugModel.getFixInBuild()%>"></td>
				</tr>
				<tr>
					<th>Resolution:</th>
					<td><textarea name="resolution" rows="3" cols="40"><%=bugModel.getResolution()%></textarea></td>
				</tr>
				<tr>
					<th>Identifier Id:</th>
					<td><input type="text" name="identifierId"
						value="<%=userModel.getFirstName()%>&nbsp;<%=userModel.getLastName()%>"
						readonly="readonly"></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" name="operation" value="Save"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>