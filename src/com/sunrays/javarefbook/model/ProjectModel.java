package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sunrays.javarefbook.utility.DatabaseUtility;

public class ProjectModel {

	private Integer id;
	private String name;
	private String description;
	private Date startdate;
	private Date enddate;
	private String manager;
	private String client;

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

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO PROJECT VALUES(ID,NAME,DESCRIPTION,START_DATE,END_DATE,MANAGER,CLIENT) VALUES(?,?,?,?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setString(3, description);
		stmt.setDate(4, new java.sql.Date(startdate.getTime()));
		stmt.setDate(5, new java.sql.Date(enddate.getTime()));
		stmt.setString(6, manager);
		stmt.setString(7, client);

		int i = stmt.executeUpdate();

		System.out.println("Project is successfully added.");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void update() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE PROJECT SET NAME=?,DESCRIPTION=?,START_DATE=?,END_DATE=?,MANAGER=?,CLIENT=? WHERE ID=?");

		stmt.setString(1, name);
		stmt.setString(2, description);
		stmt.setDate(3, new java.sql.Date(startdate.getTime()));
		stmt.setDate(4, new java.sql.Date(enddate.getTime()));
		stmt.setString(5, manager);
		stmt.setString(6, client);
		stmt.setInt(7, id);

		int i = stmt.executeUpdate();

		System.out.println("Project is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM PROJECT WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("Project is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public ProjectModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM PROJECT WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		ProjectModel model = null;

		while (resultSet.next()) {

			model = new ProjectModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setDescription(resultSet.getString(3));
			model.setStartdate(resultSet.getDate(4));
			model.setEnddate(resultSet.getDate(5));
			model.setManager(resultSet.getString(6));
			model.setClient(resultSet.getString(7));

		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM PROJECT WHERE 1=1");

		if (name != null && name.length() > 0) {
			searchQuery.append(" AND NAME LIKE '" + name + "%' ");
		}

		if (description != null && description.length() > 0) {
			searchQuery.append(" AND DESCRIPTION LIKE '" + description + "%' ");
		}

		if (startdate != null) {
			searchQuery.append(" AND START_DATE LIKE '" + startdate + "%' ");
		}

		if (enddate != null) {
			searchQuery.append(" AND END_DATE LIKE '" + enddate + "%' ");
		}

		if (manager != null && manager.length() > 0) {
			searchQuery.append(" AND MANAGER LIKE '" + manager + "%' ");
		}

		if (client != null && client.length() > 0) {
			searchQuery.append(" AND CLIENT LIKE '" + client + "%' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			ProjectModel model = new ProjectModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setDescription(resultSet.getString(3));
			model.setStartdate(resultSet.getDate(4));
			model.setEnddate(resultSet.getDate(5));
			model.setManager(resultSet.getString(6));
			model.setClient(resultSet.getString(7));
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
				.prepareStatement("SELECT MAX(ID) FROM PROJECT");

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
