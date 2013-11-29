<%@page import="com.sunrays.javarefbook.model.OrganizationModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Organization Form</h2>
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
			OrganizationModel organizationModel = (OrganizationModel) request
					.getAttribute("organizationModel");
			if (organizationModel == null) {
				organizationModel = new OrganizationModel();
				organizationModel.setId(null);
				organizationModel.setName("");
				organizationModel.setPhoneNo("");
				organizationModel.setUrl("");
				organizationModel.setAddress("");
				organizationModel.setEmail("");
				organizationModel.setFaxNo("");
			}
		%><br> <br>
		<form action="OrganizationCtl.do" method="post">
			<table>
				<input type="hidden" name="id"
					value="<%=organizationModel.getId()%>">
				<tr>
					<th>Name:</th>
					<td><input type="text" name="name"
						value="<%=organizationModel.getName()%>"></td>
				</tr>
				<tr>
					<th>Address:</th>
					<td><textarea name="address" rows="3" cols="40"><%=organizationModel.getAddress()%></textarea></td>
				</tr>
				<tr>
					<th>Fax No.:</th>
					<td><input type="text" name="faxNo"
						value="<%=organizationModel.getFaxNo()%>"></td>
				</tr>
				<tr>
					<th>E-Mail:</th>
					<td><input type="text" name="email"
						value="<%=organizationModel.getEmail()%>"></td>
				</tr>
				<tr>
					<th>Telephon No:</th>
					<td><input type="text" name="telephonNo"
						value="<%=organizationModel.getPhoneNo()%>"></td>
				</tr>
				<tr>
					<th>URL:</th>
					<td><input type="text" name="url"
						value="<%=organizationModel.getUrl()%>"></td>
				</tr>
				<tr>
					<td></td>
					<%
						if (organizationModel.getId() != null) {
					%>
					<td><input name="operation" value="Update" type="submit"></td>
					<%
						} else {
					%>
					<td><input name="operation" value="Save" type="submit"></td>
					<%
						}
					%>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>