package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunrays.javarefbook.model.OrganizationModel;
import com.sunrays.javarefbook.model.RoleModel;
import com.sunrays.javarefbook.model.UserModel;

public class UserCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RoleModel roleModel = new RoleModel();
		OrganizationModel organizationModel = new OrganizationModel();
		UserModel userModel = new UserModel();
		RequestDispatcher dispatcher = null;

		try {
			List roleList = roleModel.search();
			List organiationList = organizationModel.search();
			request.setAttribute("roleList", roleList);
			request.setAttribute("organiationList", organiationList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String operation = null;
		Integer id = null;
		try {
			operation = request.getParameter("operation");
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}

		if ("Get".equals(operation)) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
				userModel = userModel.findByPK(id);
				System.out.println(userModel.getFirstName());
				request.setAttribute("userModel", userModel);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			dispatcher = request
					.getRequestDispatcher("BaseLayout.jsp?body=User.jsp");
		}

		dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=User.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserModel userModel = new UserModel();
		Integer id = null;
		String firstName = null;
		String lastName = null;
		String gender = null;
		String userName = null;
		String userPassword = null;
		String address = null;
		String telephonNo = null;
		Integer roleId = null;
		String organization = null;
		String operation = null;

		try {
			firstName = request.getParameter("firstName");
			lastName = request.getParameter("lastName");
			gender = request.getParameter("gender");
			userName = request.getParameter("userName");
			userPassword = request.getParameter("userPassword");
			address = request.getParameter("address");
			telephonNo = request.getParameter("telephonNo");
			roleId = Integer.parseInt(request.getParameter("roleId"));
			organization = request.getParameter("organization");
			operation = request.getParameter("operation");
		} catch (Exception e) {
			e.printStackTrace();
		}

		if ("Save".equals(operation)) {
			try {
				userModel.setId(userModel.nextPK());
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setDateOfBirth(new Date());
				userModel.setGender(gender);
				userModel.setUserName(userName);
				userModel.setUserPassword(userPassword);
				userModel.setAddress(address);
				userModel.setTelephonNo(telephonNo);
				userModel.setRoleId(roleId);
				userModel.setOrgName(organization);
				userModel.add();
				request.setAttribute("message", "User successfully Added.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

		if ("Update".equals(operation)) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
				userModel.setId(id);
				userModel.setFirstName(firstName);
				userModel.setLastName(lastName);
				userModel.setDateOfBirth(new Date());
				userModel.setGender(gender);
				userModel.setUserName(userName);
				userModel.setUserPassword(userPassword);
				userModel.setAddress(address);
				userModel.setTelephonNo(telephonNo);
				userModel.setRoleId(roleId);
				userModel.setOrgName(organization);
				userModel.update();
				request.setAttribute("message", "User successfully Updated.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

	}

}
