<%@page import="com.sunrays.javarefbook.model.BugModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Bug List</h2>
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
		<form action="BugListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>Id<input type="checkbox"></th>
					<th>Type</th>
					<th>Project</th>
					<th>Module</th>
					<th>Title</th>
					<th>Priority</th>
					<th>Severity</th>
					<th>Description</th>
					<th>Owner</th>
					<th>Status</th>
					<th>Identifier Id</th>
					<th>Date</th>
					<th>Target Date</th>
					<th>Resolution</th>
					<th>Identifier In Build</th>
					<th>Fix In Build</th>
					<th>File Path</th>
					<th>Edit</th>
				</tr>
				<%
					List bugList = (List) request.getAttribute("bugList");
					if (bugList != null) {
						Iterator iterator = bugList.iterator();
						while (iterator.hasNext()) {
							BugModel bugModel = (BugModel) iterator.next();
				%>

				<tr>
					<td><input type="checkbox" name="ids"
						value="<%=bugModel.getId()%>"></td>
					<td><%=bugModel.getType()%></td>
					<td><%=bugModel.getProject()%></td>
					<td><%=bugModel.getModule()%></td>
					<td><%=bugModel.getTitle()%></td>
					<td><%=bugModel.getPriority()%></td>
					<td><%=bugModel.getSeverity()%></td>
					<td><%=bugModel.getDescription()%></td>
					<td><%=bugModel.getOwner()%></td>
					<td><%=bugModel.getStatus()%></td>
					<td><%=bugModel.getIdentifierId()%></td>
					<td><%=bugModel.getCurrent_date()%></td>
					<td><%=bugModel.getTargetDate()%></td>
					<td><%=bugModel.getResolution()%></td>
					<td><%=bugModel.getIdentifierInBuild()%></td>
					<td><%=bugModel.getFixInBuild()%></td>
					<td><%=bugModel.getFilepath()%></td>
					<td><a href="BugCtl.do?id=<%=bugModel.getId()%>&operation=Get">Edit</a></td>
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