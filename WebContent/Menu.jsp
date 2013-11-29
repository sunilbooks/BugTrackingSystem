<%@page import="com.sunrays.javarefbook.model.RoleModel"%>
<%@page import="com.sunrays.javarefbook.model.UserModel"%>
<ul id="designtree">

	<%
		UserModel userModel = (UserModel) session.getAttribute("user");
		if (userModel != null) {

			RoleModel roleModel = userModel.getRoleModel();

			if ("Developer".equalsIgnoreCase(roleModel.getName())) {
	%>

	<li>Master Entry
		<ul>
			<li><a href="TimesheetCtl.do">Timesheet</a></li>
			<li><a href="ProjectListCtl.do">Project List</a></li>
			<li><a href="ModuleListCtl.do">Module List</a></li>
		</ul>
	</li>

	<%
		} else if ("Admin".equalsIgnoreCase(roleModel.getName())) {
	%>

	<li>Admin
		<ul>
			<li>
			<li><a href="UserCtl.do">User</a></li>
			<li><a href="UserListCtl.do">User List</a></li>
			<li><a href="OrganizationCtl.do">Organization</a></li>
			<li><a href="OrganizationListCtl.do">Organization List</a></li>
			<li><a href="RoleCtl.do">Role Management</a></li>
			<li><a href="RoleListCtl.do">Role List</a></li>
			<li><a href="DashBoardCtl.do">Dashboard</a></li>
			<li><a href="TimesheetDashBoardCtl.do">Timesheet Dashboard</a></li>
		</ul>
	<li>Master Entry
		<ul>
			<li><a href="ProjectCtl.do">Project</a></li>
			<li><a href="ModuleCtl.do">Module</a></li>
			<li><a href="#">System Testing</a></li>
			<li><a href="ClientCtl.do">Client Management</a></li>
			<li><a href="BugCtl.do">Bug Form</a></li>
			<li><a href="TimesheetCtl.do">Timesheet</a></li>
		</ul>
	</li>

	<li>Report
		<ul>
			<li><a href="ProjectListCtl.do">Project List</a></li>
			<li><a href="ModuleListCtl.do">Module List</a></li>
			<li><a href="SystemTestingListCtl.do">System Setting List</a></li>
			<li><a href="ClientListCtl.do">Client List</a></li>
			<li><a href="BugListCtl.do">Bug List</a></li>
			<li><a href="TimesheetListCtl.do">Timesheet List</a></li>
		</ul>
	</li>

	<%
		} else if ("Tester".equalsIgnoreCase(roleModel.getName())) {
	%>
	<li>Tester
		<ul>
			<li><a href="BugCtl.do">Bug Form</a></li>
			<li><a href="BugListCtl.do">Bug List</a></li>
			<li><a href="ModuleListCtl.do">Module List</a></li>
			<li><a href="TimesheetCtl.do">Timesheet</a></li>
		</ul>
	</li>

	<%
		}
		}
	%>

	<li><a href="LogoutCtl">Logout</a></li>


</ul>
<script type="text/javascript">
	new Zapatec.Tree({
		tree : "designtree",
		expandOnLabelClick : true,
		initLevel : 0,
		theme : "default"
	});
</script>