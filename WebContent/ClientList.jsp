<%@page import="com.sunrays.javarefbook.model.ClientModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Client List</h2>
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
		<br> <br>
		<form action="ClientListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>Id<br> <input type="checkbox"></th>
					<th>Name</th>
					<th>Address</th>
					<th>Phone No</th>
					<th>Edit</th>
				</tr>
				<%
					List clientList = (List) request.getAttribute("clientList");
					if (clientList != null) {
						Iterator iterator = clientList.iterator();
						while (iterator.hasNext()) {
							ClientModel clientModel = (ClientModel) iterator.next();
				%>

				<tr>
					<td><input type="checkbox" value="<%=clientModel.getId()%>"
						name="ids"></td>
					<td><%=clientModel.getName()%></td>
					<td><%=clientModel.getAddress()%></td>
					<td><%=clientModel.getPhoneNo()%></td>
					<td><a
						href="ClientCtl.do?id=<%=clientModel.getId()%>&operation=Get">Edit</a></td>
				</tr>
				<%
					}
					}
				%>
			</table>
			<br> <br> <input name="operation" value="Delete"
				type="submit">
		</form>
	</center>
</body>
</html>