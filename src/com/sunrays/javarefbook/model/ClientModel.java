package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunrays.javarefbook.utility.DatabaseUtility;

public class ClientModel {
	private Integer id;
	private String name;
	private String address;
	private String phoneNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO CLIENT(ID,NAME,ADDRESS,PHONE_NO) VALUES(?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setString(3, address);
		stmt.setString(4, phoneNo);

		int i = stmt.executeUpdate();

		System.out.println("Client is successfully added.");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void update() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE CLIENT SET NAME=?,ADDRESS=?,PHONE_NO=? WHERE ID=?");

		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, phoneNo);
		stmt.setInt(4, id);

		int i = stmt.executeUpdate();

		System.out.println("Client is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM CLIENT WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("Client is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public ClientModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM CLIENT WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		ClientModel model = null;

		while (resultSet.next()) {
			model = new ClientModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setAddress(resultSet.getString(3));
			model.setPhoneNo(resultSet.getString(4));
		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM CLIENT WHERE 1=1");

		if (name != null && name.length() > 0) {
			searchQuery.append(" AND NAME LIKE '" + name + "%' ");
		}

		if (address != null && address.length() > 0) {
			searchQuery.append(" AND ADDRESS LIKE '" + address + "%' ");
		}

		if (phoneNo != null && phoneNo.length() > 0) {
			searchQuery.append(" AND PHONE_NO LIKE '" + phoneNo + "%' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			ClientModel model = new ClientModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setAddress(resultSet.getString(3));
			model.setPhoneNo(resultSet.getString(4));

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
				.prepareStatement("SELECT MAX(ID) FROM CLIENT");

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