<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<html>
<body>
	<center>
		<h2>Role Form</h2>
		<form action="RoleCtl.do" method="post">
			<table>
				<input type="hidden" name="id">
				<tr>
					<th>Name:</th>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td></td>
					<td><input name="operation" value="Save" type="submit"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>