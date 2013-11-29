package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunrays.javarefbook.utility.DatabaseUtility;

public class BugModel {
	private Integer id;;
	private String type;
	private String project;
	private String module;
	private String title;
	private String priority;
	private String severity;
	private String description;
	private String owner;
	private String status;
	private String identifierId;
	private Date current_date;
	private Date targetDate;
	private String resolution;
	private Integer identifierInBuild;
	private Integer fixInBuild;
	private String filepath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIdentifierId() {
		return identifierId;
	}

	public void setIdentifierId(String identifierId) {
		this.identifierId = identifierId;
	}

	public Date getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(Date current_date) {
		this.current_date = current_date;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public Integer getIdentifierInBuild() {
		return identifierInBuild;
	}

	public void setIdentifierInBuild(Integer identifierInBuild) {
		this.identifierInBuild = identifierInBuild;
	}

	public Integer getFixInBuild() {
		return fixInBuild;
	}

	public void setFixInBuild(Integer fixInBuild) {
		this.fixInBuild = fixInBuild;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO BUG (ID,CURRENT_DATE,DESCRIPTION,IDENTIFIER_ID,MODULE,OWNER,PRIORITY,PROJECT,SEVERITY,STATUS,TARGET_DATE,TITLE,TYPE,RESOLUTION,IDENTIFIER_IN_BUILD,FIX_IN_BUILD,FILEPATH) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setDate(2, new java.sql.Date(current_date.getTime()));
		stmt.setString(3, description);
		stmt.setString(4, identifierId);
		stmt.setString(5, module);
		stmt.setString(6, owner);
		stmt.setString(7, priority);
		stmt.setString(8, project);
		stmt.setString(9, severity);
		stmt.setString(10, status);
		stmt.setDate(11, new java.sql.Date(targetDate.getTime()));
		stmt.setString(12, title);
		stmt.setString(13, type);
		stmt.setString(14, resolution);
		stmt.setInt(15, identifierInBuild);
		stmt.setInt(16, fixInBuild);
		stmt.setString(17, filepath);

		int i = stmt.executeUpdate();

		System.out.println("Bug is successfully added.");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void update() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE BUG SET CURRENT_DATE=?,DESCRIPTION=?,IDENTIFIER_ID=?,MODULE=?,OWNER=?,PRIORITY=?,PROJECT=?,SEVERITY=?,STATUS=?,TARGET_DATE=?,TITLE=?,TYPE=?,RESOLUTION=?,IDENTIFIER_IN_BUILD=?,FIX_IN_BUILD=?,FILEPATH=? WHERE ID=?");

		stmt.setDate(1, new java.sql.Date(current_date.getTime()));
		stmt.setString(2, description);
		stmt.setString(3, identifierId);
		stmt.setString(4, module);
		stmt.setString(5, owner);
		stmt.setString(6, priority);
		stmt.setString(7, project);
		stmt.setString(8, severity);
		stmt.setString(9, status);
		stmt.setDate(10, new java.sql.Date(targetDate.getTime()));
		stmt.setString(11, title);
		stmt.setString(12, type);
		stmt.setString(13, resolution);
		stmt.setInt(14, identifierInBuild);
		stmt.setInt(15, fixInBuild);
		stmt.setString(16, filepath);
		stmt.setInt(17, id);

		int i = stmt.executeUpdate();

		System.out.println("Bug is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM BUG WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("Bug is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public BugModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM BUG WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		BugModel model = null;

		while (resultSet.next()) {

			model = new BugModel();
			model.setId(resultSet.getInt(1));
			model.setCurrent_date(resultSet.getDate(2));
			model.setDescription(resultSet.getString(3));
			model.setIdentifierId(resultSet.getString(4));
			model.setModule(resultSet.getString(5));
			model.setOwner(resultSet.getString(6));
			model.setPriority(resultSet.getString(7));
			model.setProject(resultSet.getString(8));
			model.setSeverity(resultSet.getString(9));
			model.setStatus(resultSet.getString(10));
			model.setTargetDate(resultSet.getDate(11));
			model.setTitle(resultSet.getString(12));
			model.setType(resultSet.getString(13));
			model.setResolution(resultSet.getString(14));
			model.setIdentifierInBuild(resultSet.getInt(15));
			model.setFixInBuild(resultSet.getInt(16));
			model.setFilepath(resultSet.getString(17));
		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM BUG WHERE 1=1");

		if (current_date != null) {
			searchQuery.append(" AND CURRENT_DATE LIKE '" + current_date
					+ "%' ");
		}

		if (identifierId != null && identifierId.length() > 0) {
			searchQuery.append(" AND IDENTIFIER_ID LIKE '" + identifierId
					+ "%' ");
		}

		if (module != null && module.length() > 0) {
			searchQuery.append(" AND MODULE LIKE '" + module + "%' ");
		}

		if (owner != null && owner.length() > 0) {
			searchQuery.append(" AND OWNER LIKE '" + owner + "%' ");
		}

		if (priority != null && priority.length() > 0) {
			searchQuery.append(" AND PRIORITY LIKE '" + priority + "%' ");
		}

		if (project != null && project.length() > 0) {
			searchQuery.append(" AND PROJECT LIKE '" + project + "%' ");
		}

		if (severity != null && severity.length() > 0) {
			searchQuery.append(" AND SEVERITY LIKE '" + severity + "%' ");
		}

		if (status != null && status.length() > 0) {
			searchQuery.append(" AND STATUS = '" + status + "' ");
		}

		if (targetDate != null) {
			searchQuery.append(" AND TARGET_DATE LIKE '" + targetDate + "%' ");
		}

		if (title != null && title.length() > 0) {
			searchQuery.append(" AND TITLE LIKE '" + title + "%' ");
		}

		if (type != null && type.length() > 0) {
			searchQuery.append(" AND TYPE LIKE '" + type + "%' ");
		}

		if (resolution != null && resolution.length() > 0) {
			searchQuery.append(" AND RESOLUTION LIKE '" + resolution + "%' ");
		}

		if (identifierInBuild != null && identifierInBuild > 0) {
			searchQuery.append(" AND IDENTIFIER_IN_BUILD = '"
					+ identifierInBuild + "' ");
		}

		if (fixInBuild != null && fixInBuild > 0) {
			searchQuery.append(" AND FIX_IN_BUILD = '" + fixInBuild + "' ");
		}

		if (filepath != null && filepath.length() > 0) {
			searchQuery.append(" AND FILEPATH LIKE '" + filepath + "%' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			BugModel model = new BugModel();
			model.setId(resultSet.getInt(1));
			model.setCurrent_date(resultSet.getDate(2));
			model.setDescription(resultSet.getString(3));
			model.setIdentifierId(resultSet.getString(4));
			model.setModule(resultSet.getString(5));
			model.setOwner(resultSet.getString(6));
			model.setPriority(resultSet.getString(7));
			model.setProject(resultSet.getString(8));
			model.setSeverity(resultSet.getString(9));
			model.setStatus(resultSet.getString(10));
			model.setTargetDate(resultSet.getDate(11));
			model.setTitle(resultSet.getString(12));
			model.setType(resultSet.getString(13));
			model.setResolution(resultSet.getString(14));
			model.setIdentifierInBuild(resultSet.getInt(15));
			model.setFixInBuild(resultSet.getInt(16));
			model.setFilepath(resultSet.getString(17));

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
				.prepareStatement("SELECT MAX(ID) FROM BUG");

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