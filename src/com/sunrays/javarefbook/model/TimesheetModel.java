package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunrays.javarefbook.utility.DatabaseUtility;

public class TimesheetModel {
	private Integer id;
	private String fields;
	private Date current_date;
	private String project;
	private String module;
	private String activities;
	private String timeSpend;
	private String description;
	private String status;
	private String accepted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public Date getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(Date current_date) {
		this.current_date = current_date;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getActivities() {
		return activities;
	}

	public void setActivities(String activities) {
		this.activities = activities;
	}

	public String getTimeSpend() {
		return timeSpend;
	}

	public void setTimeSpend(String timeSpend) {
		this.timeSpend = timeSpend;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO TIMESHEET VALUES(ID,CURRENT_DATE,DESCRIPTION,STATUS,MODULE,PROJECT,TIME_SHEET,ACCEPTED) VALUES(?,?,?,?,?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setDate(2, new java.sql.Date(current_date.getTime()));
		stmt.setString(3, description);
		stmt.setString(4, status);
		stmt.setString(5, module);
		stmt.setString(6, project);
		stmt.setString(7, timeSpend);
		stmt.setString(8, accepted);

		int i = stmt.executeUpdate();

		System.out.println("System is successfully added.");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void update() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE TIMESHEET SET CURRENT_DATE=?,DESCRIPTION=?,STATUS=?,MODULE=?,PROJECT=?,TIME_SHEET=?,ACCEPTED=? WHERE ID=?");

		stmt.setDate(1, new java.sql.Date(current_date.getTime()));
		stmt.setString(2, description);
		stmt.setString(3, status);
		stmt.setString(4, module);
		stmt.setString(5, project);
		stmt.setString(6, timeSpend);
		stmt.setString(7, accepted);
		stmt.setInt(8, id);

		int i = stmt.executeUpdate();

		System.out.println("Timesheet is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM TIMESHEET WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("Timesheet is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public TimesheetModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM TIMESHEET WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		TimesheetModel model = null;

		while (resultSet.next()) {

			model = new TimesheetModel();
			model.setId(resultSet.getInt(1));
			model.setCurrent_date(resultSet.getDate(2));
			model.setDescription(resultSet.getString(3));
			model.setStatus(resultSet.getString(4));
			model.setModule(resultSet.getString(5));
			model.setProject(resultSet.getString(6));
			model.setTimeSpend(resultSet.getString(7));
			model.setAccepted(resultSet.getString(8));

		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM TIMESHEET WHERE 1=1");

		if (current_date != null) {
			searchQuery.append(" AND CURRENT_DATE like '" + current_date
					+ "%' ");
		}

		if (description != null && description.length() > 0) {
			searchQuery.append(" AND DESCRIPTION LIKE '" + description + "%' ");
		}

		if (status != null && status.length() > 0) {
			searchQuery.append(" AND STATUS = '" + status + "' ");
		}

		if (module != null && module.length() > 0) {
			searchQuery.append(" AND MODULE LIKE '" + module + "%' ");
		}

		if (project != null && project.length() > 0) {
			searchQuery.append(" AND PROJECT LIKE '" + project + "%' ");
		}

		if (timeSpend != null && timeSpend.length() > 0) {
			searchQuery.append(" AND TIME_SHEET LIKE '" + timeSpend + "%' ");
		}

		if (accepted != null && accepted.length() > 0) {
			searchQuery.append(" AND ACCEPTED = '" + accepted + "' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			TimesheetModel model = new TimesheetModel();
			model.setId(resultSet.getInt(1));
			model.setCurrent_date(resultSet.getDate(2));
			model.setDescription(resultSet.getString(3));
			model.setStatus(resultSet.getString(4));
			model.setModule(resultSet.getString(5));
			model.setProject(resultSet.getString(6));
			model.setTimeSpend(resultSet.getString(7));
			model.setAccepted(resultSet.getString(8));

			list.add(model);
		}
		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return list;
	}

	public int nextPK() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT MAX(ID) FROM TIMESHEET");

		ResultSet resultSet = stmt.executeQuery();

		id = 1;

		if (resultSet.next()) {
			id += resultSet.getInt(1);
		}

		// Close Statement
		stmt.close();
		// Close Connection
		DatabaseUtility.closeConnection(conn);

		return id;
	}

}
