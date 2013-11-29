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

public class ModuleCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProjectModel projectModel = new ProjectModel();

		try {
			List projectList = projectModel.search();
			request.setAttribute("projectList", projectList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String operation = request.getParameter("operation");
		ModuleModel moduleModel = new ModuleModel();
		RequestDispatcher dispatcher = null;

		if ("Get".equals(operation)) {
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				moduleModel = moduleModel.findByPK(id);
				System.out.println("in get " + id + " " + moduleModel.getId());
				request.setAttribute("moduleModel", moduleModel);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			dispatcher = request
					.getRequestDispatcher("BaseLayout.jsp?body=Module.jsp");
		}

		dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=Module.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String projectName = request.getParameter("projectName");
		String operation = request.getParameter("operation");

		ModuleModel moduleModel = new ModuleModel();
		RequestDispatcher dispatcher = null;

		if ("Save".equals(operation)) {
			try {
				moduleModel.setId(moduleModel.nextPK());
				moduleModel.setName(name);
				moduleModel.setDescription(description);
				moduleModel.setProjectName(projectName);
				moduleModel.add();
				request.setAttribute("message", "Module successfully added.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}

			doGet(request, response);
		}

		if ("Update".equals(operation)) {
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				moduleModel.setId(id);
				moduleModel.setName(name);
				moduleModel.setDescription(description);
				moduleModel.setProjectName(projectName);
				moduleModel.update();
				request.setAttribute("message", "Module successfully updated.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}

			doGet(request, response);
		}
	}

}