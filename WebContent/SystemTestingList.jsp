<%@page import="com.sunrays.javarefbook.model.SystemTestingModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>System Testing List</h2>
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
		<form action="SystemTestingListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>Id<br> <input type="checkbox">
					<th>No Of Record</th>
					<th>SMS Notification</th>
					<th>Email Notification</th>
					<th>Edit</th>

				</tr>
				<%
					List systemTestingList = (List) request
							.getAttribute("systemTestingList");
					if (systemTestingList != null) {
						Iterator iterator = systemTestingList.iterator();
						while (iterator.hasNext()) {
							SystemTestingModel systemTestingModel = (SystemTestingModel) iterator
									.next();
				%>

				<tr>
					<td><input type="checkbox"
						value="<%=systemTestingModel.getId()%>" name="ids"></td>
					<td><%=systemTestingModel.getNoOfRecord()%></td>
					<td><%=systemTestingModel.getSmsNotification()%></td>
					<td><%=systemTestingModel.getEmailNotification()%></td>
					<td><a href="#">Edit</a></td>
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