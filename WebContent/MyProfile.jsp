<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<%
	UserModel userModel = (UserModel) session.getAttribute("user");
%>
<html>
<body>
	<center>
		<h2>User Profile</h2>
		<br>
		<form action="UserCtl.do">
			<input type="hidden" name="id" value="<%=userModel.getId()%>">
			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName"
						value="<%=userModel.getFirstName()%>"></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName"
						value="<%=userModel.getLastName()%>"></td>
				</tr>
				<tr>
					<th>Gender</th>
					<%
						if (userModel.getGender().equals("Male")) {
					%>
					<td>Male:<input type="radio" name="gender" value="Male"
						checked="checked" /></td>
					<td>Female:<input type="radio" name="gender" value="Female" /></td>
					<%
						} else {
					%>
					<td>Male:<input type="radio" name="gender" value="Male" /></td>
					<td>Female:<input type="radio" name="gender" value="Female"
						checked="checked" /></td>
					<%
						}
					%>
				</tr>
				<tr>
					<th>User Name</th>
					<td><input type="text" name="userName"
						value="<%=userModel.getUserName()%>"></td>
				</tr>
				<tr>
					<th>User Password</th>
					<td><input type="password" name="userPassword"
						value="<%=userModel.getUserPassword()%>"></td>
				</tr>
				<tr>
					<th>Address</th>
					<td><textarea name="address" rows="3" cols="40"><%=userModel.getAddress()%></textarea></td>
				</tr>
				<tr>
					<th>Telephone No</th>
					<td><input type="text" name="telephonNo"
						value="<%=userModel.getTelephonNo()%>"></td>
				</tr>
				<tr>
					<th>Role Id</th>
					<td><input type="text" name="roleName"
						value="<%=userModel.getRoleModel().getName()%>"
						readonly="readonly"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="Save"></td>
				</tr>
			</table>

			<!--	<sx:datetimepicker name="dateOfBirth" adjustWeeks="true"
			displayFormat="MM/dd/yyyy" toggleType="explode" label="Date of Birth"
			value="%{#session.userdto.dateOfBirth}"></sx:datetimepicker>-->
		</form>
		<!--<if test="%{#session.userdto.filepath!=null}">
			<img border="1"
				alt="<property value="%{#session.userdto.firstName}"/>"
				height="150" width="150"
				src="<property value="%{#session.userdto.filepath}"/> ">
		</if> -->
	</center>
</body>
</html>