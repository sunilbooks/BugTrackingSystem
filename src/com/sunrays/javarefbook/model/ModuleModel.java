package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunrays.javarefbook.utility.DatabaseUtility;

public class ModuleModel {
	private Integer id;
	private String name;
	private String description;
	private String projectName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValue() {
		return name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO MODULE(ID,NAME,DESCRIPTION,PROJECT_NAME) VALUES(?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setString(3, description);
		stmt.setString(4, projectName);

		int i = stmt.executeUpdate();

		System.out.println("Module is successfully added.");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void update() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE MODULE SET NAME=?,DESCRIPTION=?,PROJECT_NAME=? WHERE ID=?");

		stmt.setString(1, name);
		stmt.setString(2, description);
		stmt.setString(3, projectName);
		stmt.setInt(4, id);

		int i = stmt.executeUpdate();

		System.out.println("Module is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM MODULE WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("Module is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public ModuleModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM MODULE WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		ModuleModel model = null;

		while (resultSet.next()) {

			model = new ModuleModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setDescription(resultSet.getString(3));
			model.setProjectName(resultSet.getString(4));

		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM MODULE WHERE 1=1");

		if (name != null && name.length() > 0) {
			searchQuery.append(" AND NAME LIKE '" + name + "%' ");
		}

		if (description != null && description.length() > 0) {
			searchQuery.append(" AND DESCRIPTION LIKE '" + description + "%' ");
		}

		if (projectName != null && projectName.length() > 0) {
			searchQuery
					.append(" AND PROJECT_NAME LIKE '" + projectName + "%' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			ModuleModel model = new ModuleModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setDescription(resultSet.getString(3));
			model.setProjectName(resultSet.getString(4));

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
				.prepareStatement("SELECT MAX(ID) FROM MODULE");

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
