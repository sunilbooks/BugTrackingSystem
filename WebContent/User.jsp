<%@page import="com.sunrays.javarefbook.model.RoleModel"%>
<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%@page import="com.sunrays.javarefbook.model.OrganizationModel"%>
<%@page import="com.sunrays.javarefbook.model.ProjectModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>User Form</h2>
		<br>
		<%
			Integer role = null;
			String organization = "";
			String gender = "";
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

			UserModel userModel = (UserModel) request.getAttribute("userModel");
			if (userModel == null) {
				userModel = new UserModel();
				userModel.setId(null);
				userModel.setFirstName("");
				userModel.setLastName("");
				userModel.setGender("");
				userModel.setUserName("");
				userModel.setUserPassword("");
				userModel.setDateOfBirth(null);
				userModel.setAddress("");
				userModel.setTelephonNo("");
				userModel.setRoleId(null);
				userModel.setOrgName("");
			} else {
				role = userModel.getRoleId();
				organization = userModel.getOrgName();
				gender = userModel.getGender();
			}
		%>
		<br> <br>
		<%
			List roleList = (List) request.getAttribute("roleList");
			List organiationList = (List) request
					.getAttribute("organiationList");
			Iterator iterator = null;
		%>

		<form action="UserCtl.do" method="post">
			<table>
				<input type="hidden" name="id" value="<%=userModel.getId()%>">
				<tr>
					<th>First Name:</th>
					<td><input type="text" name="firstName"
						value="<%=userModel.getFirstName()%>"></td>
				</tr>
				<tr>
					<th>Last Name:</th>
					<td><input type="text" name="lastName"
						value="<%=userModel.getLastName()%>"></td>
				</tr>
				<tr>
					<th>Gender:</th>
					<%
						if (gender.equals("Male")) {
					%>
					<td>Male&nbsp;<input type="radio" name="gender" value="Male"
						checked="checked">&nbsp;Female&nbsp;<input type="radio"
						name="gender" value="Female"></td>
					<%
						} else if (gender.equals("Female")) {
					%>
					<td>Male&nbsp;<input type="radio" name="gender" value="Male">&nbsp;Female&nbsp;<input
						type="radio" name="gender" value="Female" checked="checked"></td>
					<%
						} else {
					%>
					<td>Male&nbsp;<input type="radio" name="gender" value="Male">&nbsp;Female&nbsp;<input
						type="radio" name="gender" value="Female"></td>
					<%
						}
					%>

				</tr>
				<tr>
					<th>User Name:</th>
					<td><input type="text" name="userName"
						value="<%=userModel.getUserName()%>"></td>
				</tr>
				<tr>
					<th>User Password:</th>
					<td><input type="password" name="userPassword"
						value="<%=userModel.getUserPassword()%>"></td>
				</tr>
				<tr>
					<th>Address:</th>
					<td><textarea name="address" rows="3" cols="40"><%=userModel.getAddress()%></textarea></td>
				</tr>
				<tr>
					<th>Telephon No:</th>
					<td><input type="text" name="telephonNo"
						value="<%=userModel.getTelephonNo()%>"></td>
				</tr>
				<tr>
					<th>Role Id:</th>
					<td><select name="roleId">
							<option value="">--Select--</option>
							<%
								if (roleList != null) {
									iterator = roleList.iterator();

									while (iterator.hasNext()) {
										RoleModel roleModel = (RoleModel) iterator.next();
							%>
							<option value="<%=roleModel.getId()%>"><%=roleModel.getName()%></option>
							<%
								}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<th>Organization:</th>
					<td><select name="organization">
							<option value="">--Select--</option>
							<%
								if (!organization.equals("")) {
							%>
							<option value="<%=organization%>" selected="selected"><%=organization%></option>
							<%
								}
							%>
							<%
								if (organiationList != null) {
									iterator = organiationList.iterator();

									while (iterator.hasNext()) {
										OrganizationModel organizationModel = (OrganizationModel) iterator
												.next();
										if (!organization.equals(organizationModel.getName())) {
							%>
							<option value="<%=organizationModel.getName()%>"><%=organizationModel.getName()%></option>
							<%
								}
									}
								}
							%>
					</select></td>
				</tr>
				<tr>
					<td></td>
					<%
						if (userModel.getId() != null) {
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