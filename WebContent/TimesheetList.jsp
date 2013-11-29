<%@page import="com.sunrays.javarefbook.model.TimesheetModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Timesheet List</h2>
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
		<form action="TimesheetListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>Id<input type="checkbox"></th>
					<th>Project</th>
					<th>Module</th>
					<th>Description</th>
					<th>Date</th>
					<th>Time Spend</th>
					<th>Status</th>
					<th>Accepted</th>
					<th>Edit</th>
				</tr>
				<%
					List timesheetList = (List) request.getAttribute("timesheetList");
					if (timesheetList != null) {
						Iterator iterator = timesheetList.iterator();
						while (iterator.hasNext()) {
							TimesheetModel timesheetModel = (TimesheetModel) iterator
									.next();
				%>
				<tr>
					<td><input type="checkbox" name="ids"
						value="<%=timesheetModel.getId()%>"></td>
					<td><%=timesheetModel.getProject()%></td>
					<td><%=timesheetModel.getModule()%></td>
					<td><%=timesheetModel.getDescription()%></td>
					<td><%=timesheetModel.getCurrent_date()%></td>
					<td><%=timesheetModel.getTimeSpend()%></td>
					<td><%=timesheetModel.getStatus()%></td>
					<td><%=timesheetModel.getAccepted()%></td>
					<td><a href="#">Edit</a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
			<input name="operation" value="Delete" type="submit">
		</form>
	</center>
</body>
</html>