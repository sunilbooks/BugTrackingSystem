<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.ClientModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<h2>Client Form</h2>
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
	<br>
	<br>
	<%
		ClientModel clientModel = (ClientModel) request
				.getAttribute("clientModel");
		if (clientModel == null) {
			clientModel = new ClientModel();
			clientModel.setId(null);
			clientModel.setName("");
			clientModel.setAddress("");
			clientModel.setPhoneNo("");
		}
	%>
	<form action="ClientCtl.do" method="post">
		<table>
			<input type="hidden" name="id" value="<%=clientModel.getId()%>">
			<tr>
				<th>Name:</th>
				<td><input type="text" name="name"
					value="<%=clientModel.getName()%>"></td>
			</tr>
			<tr>
				<th>Address:</th>
				<td><textarea name="address" rows="3" cols="40"><%=clientModel.getAddress()%></textarea></td>
			</tr>
			<tr>
				<th>Phone No:</th>
				<td><input type="text" name="phoneNo"
					value="<%=clientModel.getPhoneNo()%>"></td>
			</tr>
			<tr>
				<td></td>
				<%
					if (clientModel.getId() != null) {
				%>
				<td><input type="submit" name="operation" value="Update"></td>
				<%
					} else {
				%><td><input type="submit" name="operation" value="Save"></td>
				<%
					}
				%>
			</tr>
		</table>
	</form>
</body>
</html>