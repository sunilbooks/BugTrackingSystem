package com.sunrays.javarefbook.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.sunrays.javarefbook.utility.DatabaseUtility;

public class UserModel {
	private Integer id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String userName;
	private String userPassword;
	private String address;
	private String gender;
	private Integer roleId;
	private String telephonNo;
	private String orgName;
	private RoleModel roleModel = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getValue() {
		return firstName + " " + lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getTelephonNo() {
		return telephonNo;
	}

	public void setTelephonNo(String telephonNo) {
		this.telephonNo = telephonNo;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public void add() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO USER(ID,FIRST_NAME,LAST_NAME,DATE_OF_BIRTH,USER_NAME,USER_PASSWORD,ADDRESS,GENDER,ROLE_ID,TELEPHONE_NO,ORGANIZATION) VALUES(?,?,?,?,?,?,?,?,?,?,?)");

		stmt.setInt(1, id);
		stmt.setString(2, firstName);
		stmt.setString(3, lastName);
		stmt.setDate(4, new java.sql.Date(dateOfBirth.getTime()));
		stmt.setString(5, userName);
		stmt.setString(6, userPassword);
		stmt.setString(7, address);
		stmt.setString(8, gender);
		stmt.setInt(9, roleId);
		stmt.setString(10, telephonNo);
		stmt.setString(11, orgName);

		int i = stmt.executeUpdate();

		System.out.println("User is successfully added.");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void update() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("UPDATE USER SET FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,USER_NAME=?,USER_PASSWORD=?,ADDRESS=?,GENDER=?,ROLE_ID=?,TELEPHONE_NO=?,ORGANIZATION=? WHERE ID=?");

		stmt.setString(1, firstName);
		stmt.setString(2, lastName);
		stmt.setDate(3, new java.sql.Date(dateOfBirth.getTime()));
		stmt.setString(4, userName);
		stmt.setString(5, userPassword);
		stmt.setString(6, address);
		stmt.setString(7, gender);
		stmt.setInt(8, roleId);
		stmt.setString(9, telephonNo);
		stmt.setString(10, orgName);
		stmt.setInt(11, id);

		int i = stmt.executeUpdate();

		System.out.println("User is successfully Updated. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public void delete() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("DELETE FROM USER WHERE ID = ?");

		stmt.setInt(1, id);

		int i = stmt.executeUpdate();

		System.out.println("User is successfully Deleted. ");

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);
	}

	public UserModel findByPK(Integer id) throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT * FROM USER WHERE ID = ?");

		stmt.setInt(1, id);

		ResultSet resultSet = stmt.executeQuery();

		UserModel model = null;

		while (resultSet.next()) {

			model = new UserModel();
			model.setId(resultSet.getInt(1));
			model.setFirstName(resultSet.getString(2));
			model.setLastName(resultSet.getString(3));
			model.setDateOfBirth(resultSet.getDate(4));
			model.setUserName(resultSet.getString(5));
			model.setUserPassword(resultSet.getString(6));
			model.setAddress(resultSet.getString(7));
			model.setGender(resultSet.getString(8));
			model.setRoleId(resultSet.getInt(9));
			model.setTelephonNo(resultSet.getString(10));
			model.setOrgName(resultSet.getString(11));
		}

		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public List search() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		StringBuffer searchQuery = new StringBuffer(
				"SELECT * FROM USER WHERE 1=1");

		if (firstName != null && firstName.length() > 0) {
			searchQuery.append(" AND FIRST_NAME LIKE '" + firstName + "%' ");
		}

		if (lastName != null && lastName.length() > 0) {
			searchQuery.append(" AND LAST_NAME LIKE '" + lastName + "%' ");
		}

		if (dateOfBirth != null) {
			searchQuery.append(" AND DATE_OF_BIRTH LIKE '" + dateOfBirth
					+ "%' ");
		}

		if (userName != null && userName.length() > 0) {
			searchQuery.append(" AND USER_NAME LIKE '" + userName + "%' ");
		}

		if (userPassword != null && userPassword.length() > 0) {
			searchQuery.append(" AND USER_PASSWORD LIKE '" + userPassword
					+ "%' ");
		}

		if (address != null && address.length() > 0) {
			searchQuery.append(" AND ADDRESS LIKE '" + address + "%' ");
		}

		if (gender != null && gender.length() > 0) {
			searchQuery.append(" AND GENDER LIKE '" + gender + "%' ");
		}

		if (roleId != null && roleId > 0) {
			searchQuery.append(" AND ROLE_ID = '" + roleId + "' ");
		}

		if (telephonNo != null && telephonNo.length() > 0) {
			searchQuery.append(" AND ROLE_ID = '" + roleId + "' ");
		}

		if (orgName != null && orgName.length() > 0) {
			searchQuery.append(" AND ORGNIZATION = '" + roleId + "' ");
		}

		List list = new ArrayList();

		// Create Statement
		Statement stmt = conn.createStatement();

		ResultSet resultSet = stmt.executeQuery(searchQuery.toString());

		while (resultSet.next()) {
			UserModel model = new UserModel();
			model.setId(resultSet.getInt(1));
			model.setFirstName(resultSet.getString(2));
			model.setLastName(resultSet.getString(3));
			model.setDateOfBirth(resultSet.getDate(4));
			model.setUserName(resultSet.getString(5));
			model.setUserPassword(resultSet.getString(6));
			model.setAddress(resultSet.getString(7));
			model.setGender(resultSet.getString(8));
			model.setRoleId(resultSet.getInt(9));
			model.setTelephonNo(resultSet.getString(10));
			model.setOrgName(resultSet.getString(11));

			list.add(model);
		}
		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return list;
	}

	public UserModel authenticate() throws Exception {

		Connection conn = DatabaseUtility.openConnection();

		PreparedStatement stmt = conn
				.prepareStatement("SELECT u.*,r.NAME FROM USER u INNER JOIN Role r ON(u.ROLE_ID=r.ID) WHERE USER_NAME = ? AND USER_PASSWORD = ?");
		stmt.setString(1, userName);
		stmt.setString(2, userPassword);

		ResultSet resultSet = stmt.executeQuery();

		UserModel model = null;

		if (resultSet.next()) {
			model = new UserModel();
			model.setId(resultSet.getInt(1));
			model.setFirstName(resultSet.getString(2));
			model.setLastName(resultSet.getString(3));
			model.setDateOfBirth(resultSet.getDate(4));
			model.setUserName(resultSet.getString(5));
			model.setUserPassword(resultSet.getString(6));
			model.setAddress(resultSet.getString(7));
			model.setGender(resultSet.getString(8));
			model.setRoleId(resultSet.getInt(9));
			model.setTelephonNo(resultSet.getString(10));
			model.setOrgName(resultSet.getString(11));
			roleModel = new RoleModel();
			roleModel.setName(resultSet.getString(12));
			model.setRoleModel(roleModel);

		}
		// Close Statement & Connection
		stmt.close();
		DatabaseUtility.closeConnection(conn);

		return model;
	}

	public int nextPK() throws Exception {
		Connection conn = DatabaseUtility.openConnection();

		// Create Statement
		PreparedStatement stmt = conn
				.prepareStatement("SELECT MAX(ID) FROM USER");

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
