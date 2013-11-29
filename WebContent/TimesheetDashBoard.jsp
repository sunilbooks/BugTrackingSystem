<%@page import="com.sunrays.javarefbook.model.TimesheetModel"%>
<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.RoleModel"%>
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

	<h2>Dashboard Form</h2>

	<br>
	<br>
	<form action="TimesheetCtl.do" method="post">
		<h3>Rejected Timesheet</h3>
		<table border="1">

			<tr>
				<th>Id</th>
				<th>Project</th>
				<th>Module</th>
				<th>Description</th>
				<th>Date</th>
				<th>Time Spend</th>
				<th>Status</th>
				<th>Accepted</th>
			</tr>

			<%
				List timesheetList = (List) request.getAttribute("rejectedTmsList");
				if (timesheetList != null) {
					Iterator iterator = timesheetList.iterator();
					while (iterator.hasNext()) {
						TimesheetModel timesheetModel = (TimesheetModel) iterator
								.next();
			%>

			<tr>
				<td><input type="checkbox" value="<%=timesheetModel.getId()%>"></td>
				<td><%=timesheetModel.getProject()%></td>
				<td><%=timesheetModel.getModule()%></td>
				<td><%=timesheetModel.getDescription()%></td>
				<td><%=timesheetModel.getCurrent_date()%></td>
				<td><%=timesheetModel.getTimeSpend()%></td>
				<td><%=timesheetModel.getStatus()%></td>
				<td><%=timesheetModel.getAccepted()%></td>
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
		<h3>Timesheet Waiting For Approval</h3>
		<table border="1">

			<tr>
				<th>Id</th>
				<th>Project</th>
				<th>Module</th>
				<th>Description</th>
				<th>Date</th>
				<th>Time Spend</th>
				<th>Status</th>
				<th>Accepted</th>
			</tr>

			<%
				timesheetList = (List) request.getAttribute("waitedTmsList");
				if (timesheetList != null) {
					Iterator iterator = timesheetList.iterator();
					while (iterator.hasNext()) {
						TimesheetModel timesheetModel = (TimesheetModel) iterator
								.next();
			%>

			<tr>
				<td><input type="checkbox" value="<%=timesheetModel.getId()%>"></td>
				<td><%=timesheetModel.getProject()%></td>
				<td><%=timesheetModel.getModule()%></td>
				<td><%=timesheetModel.getDescription()%></td>
				<td><%=timesheetModel.getCurrent_date()%></td>
				<td><%=timesheetModel.getTimeSpend()%></td>
				<td><%=timesheetModel.getStatus()%></td>
				<td><%=timesheetModel.getAccepted()%></td>
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
		<h3>Timesheet To be Submitted</h3>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Project</th>
				<th>Module</th>
				<th>Description</th>
				<th>Date</th>
				<th>Time Spend</th>
				<th>Status</th>
				<th>Accepted</th>
			</tr>

			<%
				timesheetList = (List) request.getAttribute("submittedTmsList");
				if (timesheetList != null) {
					Iterator iterator = timesheetList.iterator();
					while (iterator.hasNext()) {
						TimesheetModel timesheetModel = (TimesheetModel) iterator
								.next();
			%>

			<tr>
				<td><input type="checkbox" value="<%=timesheetModel.getId()%>"></td>
				<td><%=timesheetModel.getProject()%></td>
				<td><%=timesheetModel.getModule()%></td>
				<td><%=timesheetModel.getDescription()%></td>
				<td><%=timesheetModel.getCurrent_date()%></td>
				<td><%=timesheetModel.getTimeSpend()%></td>
				<td><%=timesheetModel.getStatus()%></td>
				<td><%=timesheetModel.getAccepted()%></td>
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
		<h3>Timesheet Approved</h3>
		<table border="1">
			<tr>
				<th>Id</th>
				<th>Project</th>
				<th>Module</th>
				<th>Description</th>
				<th>Date</th>
				<th>Time Spend</th>
				<th>Status</th>
				<th>Accepted</th>
			</tr>

			<%
				timesheetList = (List) request.getAttribute("approvedTmsList");
				if (timesheetList != null) {
					Iterator iterator = timesheetList.iterator();
					while (iterator.hasNext()) {
						TimesheetModel timesheetModel = (TimesheetModel) iterator
								.next();
			%>

			<tr>
				<td><input type="checkbox" value="<%=timesheetModel.getId()%>"></td>
				<td><%=timesheetModel.getProject()%></td>
				<td><%=timesheetModel.getModule()%></td>
				<td><%=timesheetModel.getDescription()%></td>
				<td><%=timesheetModel.getCurrent_date()%></td>
				<td><%=timesheetModel.getTimeSpend()%></td>
				<td><%=timesheetModel.getStatus()%></td>
				<td><%=timesheetModel.getAccepted()%></td>
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