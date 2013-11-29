package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sunrays.javarefbook.utility.DatabaseUtility;

public class SystemTestingModel {

	private Integer id;
	private Integer noOfRecord;
	private String smsNotification;
	private String emailNotification;
	private String theme;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNoOfRecord() {
		return noOfRecord;
	}

	public void setNoOfRecord(Integer noOfRecord) {
		this.noOfRecord = noOfRecord;
	}

	public String getSmsNotification() {
		return smsNotification;
	}

	public void setSmsNotification(String smsNotification) {
		this.smsNotification = smsNotification;
	}

	public String getEmailNotification() {
		return emailNotification;
	}

	public void setEmailNotification(String emailNotification) {
		this.emailNotification = emailNotification;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO SYSTEMTESTING VALUES(ID,E_MAIL_NOTIFICATION,NO_OF_RECORD,SMS_NOTIFICATION,THEME) VALUES(?,?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setString(2, emailNotification);
		stmt.setInt(3, noOfRecord);
		stmt.setString(4, smsNotification);
		stmt.setString(5, theme);

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
				.prepareStatement("UPDATE SYSTEMTESTING SET E_MAIL_NOTIFICATION=?,NO_OF_RECORD=?,SMS_NOTIFICATION=?,THEME=? WHERE ID=?");

		stmt.setString(1, emailNotification);
		stmt.setInt(2, noOfRecord);
		stmt.setString(3, smsNotification);
		stmt.setString(4, theme);
		stmt.setInt(5, id);

		int i = stmt.executeUpdate();

		System.out.println("System is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM SYSTEMTESTING WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("Systemtesting is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public SystemTestingModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM SYSTEMTESTING WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		SystemTestingModel model = null;

		while (resultSet.next()) {

			model = new SystemTestingModel();
			model.setId(resultSet.getInt(1));
			model.setEmailNotification(resultSet.getString(2));
			model.setNoOfRecord(resultSet.getInt(3));
			model.setSmsNotification(resultSet.getString(4));
			model.setTheme(resultSet.getString(5));

		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM SYSTEMTESTING WHERE 1=1");

		if (noOfRecord != null && noOfRecord > 0) {
			searchQuery.append(" AND NO_OF_RECORD = '" + noOfRecord + "' ");
		}

		if (smsNotification != null && smsNotification.length() > 0) {
			searchQuery.append(" AND SMS_NOTIFICATION LIKE '" + smsNotification
					+ "%' ");
		}

		if (emailNotification != null && emailNotification.length() > 0) {
			searchQuery.append(" AND E_MAIL_NOTIFICATION LIKE '"
					+ emailNotification + "%' ");
		}

		if (theme != null && theme.length() > 0) {
			searchQuery.append(" AND THEME LIKE '" + theme + "%' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			SystemTestingModel model = new SystemTestingModel();
			model.setId(resultSet.getInt(1));
			model.setEmailNotification(resultSet.getString(2));
			model.setNoOfRecord(resultSet.getInt(3));
			model.setSmsNotification(resultSet.getString(4));
			model.setTheme(resultSet.getString(5));

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
				.prepareStatement("SELECT MAX(ID) FROM SYSTEMTESTING");

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
