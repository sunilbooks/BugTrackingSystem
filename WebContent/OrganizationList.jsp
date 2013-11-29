<%@page import="com.sunrays.javarefbook.model.OrganizationModel"%>
<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.ModuleModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Organization List</h2>
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
		<form action="OrganizationListCtl.do" method="post">
			<table border="1">
				<tr>
					<th>ID<br> <input type="checkbox">
					</th>
					<th>Name</th>
					<th>Phone No</th>
					<th>Address</th>
					<th>Fax No</th>
					<th>E_Mail</th>
					<th>URL</th>
					<th>Edit</th>
				</tr>
				<%
					List organizationList = (List) request
							.getAttribute("organizationList");
					if (organizationList != null) {
						Iterator iterator = organizationList.iterator();
						while (iterator.hasNext()) {
							OrganizationModel organizationModel = (OrganizationModel) iterator
									.next();
				%>

				<tr>
					<td><input type="checkbox"
						value="<%=organizationModel.getId()%>" name="ids"></td>
					<td><%=organizationModel.getName()%></td>
					<td><%=organizationModel.getPhoneNo()%></td>
					<td><%=organizationModel.getAddress()%></td>
					<td><%=organizationModel.getFaxNo()%></td>
					<td><%=organizationModel.getEmail()%></td>
					<td><%=organizationModel.getUrl()%></td>
					<td><a
						href="OrganizationCtl.do?operation=Get&id=<%=organizationModel.getId()%>">Edit</a></td>
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