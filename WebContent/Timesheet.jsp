<%@page import="com.sunrays.javarefbook.model.ModuleModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Timesheet Form</h2>
		<%
			List projectList = (List) request.getAttribute("projectList");
			List moduleList = (List) request.getAttribute("moduleList");
			Iterator iterator = null;
		%>
		<form action="TimesheetCtl" method="post">
			<table>
				<input type="hidden" name="id">
				<tr>
					<th>Project:</th>
					<!-- date -->
					<td><select name="project">
							<option value="">--Select--</option>
							<%
								if (projectList != null) {
									iterator = projectList.iterator();

									while (iterator.hasNext()) {
										ProjectModel projectModel = (ProjectModel) iterator.next();
							%>
							<option value="<%=projectModel.getName()%>"><%=projectModel.getName()%></option>
							<%
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
								if (moduleList != null) {
									iterator = moduleList.iterator();

									while (iterator.hasNext()) {
										ModuleModel moduleModel = (ModuleModel) iterator.next();
							%>
							<option value="<%=moduleModel.getName()%>"><%=moduleModel.getName()%></option>
							<%
								}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>Activity:</th>
					<td><select name="activity">
							<option value="Administration">Administration</option>
							<option value="Designing">Designing</option>
							<option value="Analysis">Analysis</option>
							<option value="Meeting">Meeting</option>
							<option value="Coding">Coding</option>
							<option value="Testing">Testing</option>
							<option value="Bug Fixing">Bug Fixing</option>
							<option value="Other">Other</option>
					</select></td>
				</tr>
				<tr>
					<th>Work Description:</th>
					<td><textarea rows="3" cols="40" name="description"></textarea>
					</td>
				</tr>
				<tr>
					<th>Spent Time (in Hours):</th>
					<td><select name="timeSpend">
							<option value="0.5">0.5</option>
							<option value="1.0">1.0</option>
							<option value="1.5">1.5</option>
							<option value="2.0">2.0</option>
							<option value="2.5">2.5</option>
							<option value="3.0">3.0</option>
							<option value="3.5">3.5</option>
							<option value="4.0">4.0</option>
							<option value="4.5">4.5</option>
							<option value="5.0">5.0</option>
							<option value="5.5">5.5</option>
							<option value="6.0">6.0</option>
							<option value="6.5">6.5</option>
							<option value="7.0">7.0</option>
							<option value="7.5">7.5</option>
							<option value="8.0">8.0</option>
					</select></td>
				</tr>
				<tr>
					<th>Status</th>
					<td><input type="radio" name="status" value="Save">&nbsp;Save<input
						type="radio" name="status" value="Submit">&nbsp;Submit for
						Approval</td>
				</tr>
				<tr>
					<td></td>
					<td><input name="operation" value="Save" type="submit"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>