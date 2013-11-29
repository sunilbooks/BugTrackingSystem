package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrays.javarefbook.model.ClientModel;
import com.sunrays.javarefbook.model.ProjectModel;
import com.sunrays.javarefbook.model.UserModel;

public class ProjectCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserModel userModel = new UserModel();
		ClientModel clientModel = new ClientModel();
		ProjectModel projectModel = new ProjectModel();
		RequestDispatcher dispatcher = null;
		try {
			List userList = userModel.search();
			List clientList = clientModel.search();
			request.setAttribute("userList", userList);
			request.setAttribute("clientList", clientList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Integer id = null;
		String operation = null;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			operation = request.getParameter("operation");
		} catch (NumberFormatException e) {
		}

		if ("Get".equals(operation)) {
			try {
				projectModel = projectModel.findByPK(id);
				request.setAttribute("projectModel", projectModel);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			dispatcher = request
					.getRequestDispatcher("BaseLayout.jsp?body=Client.jsp");
		}

		dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=Project.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer id = null;
		String name = null;
		String client = null;
		String description = null;
		String startDate = null;
		String lastDate = null;
		String manager = null;
		String operation = null;
		try {
			name = request.getParameter("name");
			client = request.getParameter("client");
			description = request.getParameter("description");
			manager = request.getParameter("manager");
			operation = request.getParameter("operation");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ProjectModel projectModel = new ProjectModel();

		if ("Save".equals(operation)) {
			try {
				projectModel.setId(projectModel.nextPK());
				projectModel.setName(name);
				projectModel.setClient(client);
				projectModel.setStartdate(new Date());
				projectModel.setEnddate(new Date());
				projectModel.setDescription(description);
				projectModel.setManager(manager);
				projectModel.add();
				request.setAttribute("message", "Project successfully Added.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

		if ("Update".equals(operation)) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
				projectModel.setId(id);
				projectModel.setName(name);
				projectModel.setClient(client);
				projectModel.setStartdate(new Date());
				projectModel.setEnddate(new Date());
				projectModel.setDescription(description);
				projectModel.setManager(manager);
				projectModel.update();
				request.setAttribute("message", "Project successfully Updated.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

		if ("Delete".equals(operation)) {
			try {
				projectModel.setId(id);
				projectModel.delete();
				request.setAttribute("message", "Project successfully Deleted.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}
	}
}