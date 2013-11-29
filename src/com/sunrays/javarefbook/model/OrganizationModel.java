package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunrays.javarefbook.utility.DatabaseUtility;

public class OrganizationModel {
	private Integer id;
	private String name;
	private String address;
	private String phoneNo;
	private String faxNo;
	private String email;
	private String url;

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

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO ORGANIZATION(ID,NAME,ADDRESS,PHONE_NO,FAX_NO,E_MAIL,URL) VALUES(?,?,?,?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setString(2, name);
		stmt.setString(3, address);
		stmt.setString(4, phoneNo);
		stmt.setString(5, faxNo);
		stmt.setString(6, email);
		stmt.setString(7, url);

		int i = stmt.executeUpdate();

		System.out.println("Organization is successfully added.");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void update() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE ORGANIZATION SET NAME=?,ADDRESS=?,PHONE_NO=?,FAX_NO=?,E_MAIL=?,URL=? WHERE ID=?");

		stmt.setString(1, name);
		stmt.setString(2, address);
		stmt.setString(3, phoneNo);
		stmt.setString(4, faxNo);
		stmt.setString(5, email);
		stmt.setString(6, url);
		stmt.setInt(7, id);

		int i = stmt.executeUpdate();

		System.out.println("Organization is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM ORGANIZATION WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("Organization is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public OrganizationModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM ORGANIZATION WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		OrganizationModel model = null;

		while (resultSet.next()) {
			model = new OrganizationModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setAddress(resultSet.getString(3));
			model.setPhoneNo(resultSet.getString(4));
			model.setFaxNo(resultSet.getString(5));
			model.setEmail(resultSet.getString(6));
			model.setUrl(resultSet.getString(7));

		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM ORGANIZATION WHERE 1=1");

		if (name != null && name.length() > 0) {
			searchQuery.append(" AND NAME LIKE '" + name + "%' ");
		}

		if (address != null && address.length() > 0) {
			searchQuery.append(" AND ADDRESS LIKE '" + address + "%' ");
		}

		if (phoneNo != null && phoneNo.length() > 0) {
			searchQuery.append(" AND PHONE_NO = '" + phoneNo + "' ");
		}

		if (faxNo != null && faxNo.length() > 0) {
			searchQuery.append(" AND FAX_NO = '" + faxNo + "' ");
		}

		if (email != null && email.length() > 0) {
			searchQuery.append(" AND E_MAIL LIKE '" + email + "%' ");
		}

		if (url != null && url.length() > 0) {
			searchQuery.append(" AND URL LIKE '" + url + "%' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			OrganizationModel model = new OrganizationModel();
			model.setId(resultSet.getInt(1));
			model.setName(resultSet.getString(2));
			model.setAddress(resultSet.getString(3));
			model.setPhoneNo(resultSet.getString(4));
			model.setFaxNo(resultSet.getString(5));
			model.setEmail(resultSet.getString(6));
			model.setUrl(resultSet.getString(7));

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
				.prepareStatement("SELECT MAX(ID) FROM ORGANIZATION");

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
