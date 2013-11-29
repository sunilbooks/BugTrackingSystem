package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrays.javarefbook.model.ModuleModel;
import com.sunrays.javarefbook.model.ProjectModel;

public class ProjectListCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProjectModel projectModel = new ProjectModel();

		try {
			List projectList = projectModel.search();
			request.setAttribute("projectList", projectList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=ProjectList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProjectModel projectModel = new ProjectModel();
		String operation = request.getParameter("operation");
		String[] ids = request.getParameterValues("ids");

		if ("Delete".equals(operation)) {
			for (int i = 0; i < ids.length; i++) {
				try {
					Integer id = Integer.parseInt(ids[i]);
					projectModel.setId(id);
					projectModel.delete();
					request.setAttribute("message",
							"Project successfully Deleted.");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Database Error.");
				}
			}
			doGet(request, response);
		}
	}

}
