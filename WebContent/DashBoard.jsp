<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.RoleModel"%>
<%@page import="com.sunrays.javarefbook.model.BugModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<%!RoleModel roleModel = null;%>
	<%
		UserModel userModel = (UserModel) session.getAttribute("user");
		if (userModel != null) {
			roleModel = userModel.getRoleModel();
		}
	%>

	<h2>Bug List</h2>
	<br>
	<br>
	<form action="TimesheetCtl.do" method="post">
		<h3>Assigned Bug</h3>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>type</th>
				<th>Project</th>
				<th>Module</th>
				<th>Title</th>
				<th>Priority</th>
				<th>Severity</th>
				<th>Owner</th>
				<th>Status</th>
				<th>Identifier Id</th>
				<th>Date</th>
				<th>Target Date</th>
			</tr>
			<%
				List bugList = (List) request.getAttribute("openBugList");
				if (bugList != null) {
					Iterator iterator = bugList.iterator();
					while (iterator.hasNext()) {
						BugModel bugModel = (BugModel) iterator.next();
			%>

			<tr>
				<td><input type="checkbox" value="<%=bugModel.getId()%>"></td>
				<td><%=bugModel.getType()%></td>
				<td><%=bugModel.getProject()%></td>
				<td><%=bugModel.getModule()%></td>
				<td><%=bugModel.getTitle()%></td>
				<td><%=bugModel.getPriority()%></td>
				<td><%=bugModel.getSeverity()%></td>
				<td><%=bugModel.getOwner()%></td>
				<td><%=bugModel.getStatus()%></td>
				<td><%=bugModel.getIdentifierId()%></td>
				<td><%=bugModel.getCurrent_date()%></td>
				<td><%=bugModel.getTargetDate()%></td>
				<%
					if ("Admin".equalsIgnoreCase(roleModel.getName())) {
				%>
				<td><a href="#">Edit</a></td>
				<%
					}
				%>
			</tr>
			<%
				}
				}
			%>
		</table>
		<br> <br> <br>
		<h3>Fixed Bug</h3>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>type</th>
				<th>Project</th>
				<th>Module</th>
				<th>Title</th>
				<th>Priority</th>
				<th>Severity</th>
				<th>Owner</th>
				<th>Status</th>
				<th>Identifier Id</th>
				<th>Date</th>
				<th>Target Date</th>
			</tr>
			<%
				bugList = (List) request.getAttribute("fixedBugList");
				if (bugList != null) {
					Iterator iterator = bugList.iterator();
					while (iterator.hasNext()) {
						BugModel bugModel = (BugModel) iterator.next();
			%>

			<tr>
				<td><input type="checkbox" value="<%=bugModel.getId()%>"></td>
				<td><%=bugModel.getType()%></td>
				<td><%=bugModel.getProject()%></td>
				<td><%=bugModel.getModule()%></td>
				<td><%=bugModel.getTitle()%></td>
				<td><%=bugModel.getPriority()%></td>
				<td><%=bugModel.getSeverity()%></td>
				<td><%=bugModel.getOwner()%></td>
				<td><%=bugModel.getStatus()%></td>
				<td><%=bugModel.getIdentifierId()%></td>
				<td><%=bugModel.getCurrent_date()%></td>
				<td><%=bugModel.getTargetDate()%></td>
				<%
					if ("Admin".equalsIgnoreCase(roleModel.getName())) {
				%><td><a href="#">Edit</a></td>
				<%
					}
				%>
			</tr>
			<%
				}
				}
			%>
		</table>
		<br> <br> <br>
		<h3>Close Bug</h3>
		<table border="1">
			<tr>
				<th>ID</th>
				<th>type</th>
				<th>Project</th>
				<th>Module</th>
				<th>Title</th>
				<th>Priority</th>
				<th>Severity</th>
				<th>Owner</th>
				<th>Status</th>
				<th>Identifier Id</th>
				<th>Date</th>
				<th>Target Date</th>
			</tr>
			<%
				bugList = (List) request.getAttribute("closeBugList");
				if (bugList != null) {
					Iterator iterator = bugList.iterator();
					while (iterator.hasNext()) {
						BugModel bugModel = (BugModel) iterator.next();
			%>

			<tr>
				<td><input type="checkbox" value="<%=bugModel.getId()%>"></td>
				<td><%=bugModel.getType()%></td>
				<td><%=bugModel.getProject()%></td>
				<td><%=bugModel.getModule()%></td>
				<td><%=bugModel.getTitle()%></td>
				<td><%=bugModel.getPriority()%></td>
				<td><%=bugModel.getSeverity()%></td>
				<td><%=bugModel.getOwner()%></td>
				<td><%=bugModel.getStatus()%></td>
				<td><%=bugModel.getIdentifierId()%></td>
				<td><%=bugModel.getCurrent_date()%></td>
				<td><%=bugModel.getTargetDate()%></td>
				<%
					if ("Admin".equalsIgnoreCase(roleModel.getName())) {
				%>
				<td><a href="#">Edit</a></td>
				<%
					}
				%>
			</tr>
			<%
				}
				}
			%>
		</table>
	</form>
</body>
</html>