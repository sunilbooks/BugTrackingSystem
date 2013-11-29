package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunrays.javarefbook.model.BugModel;
import com.sunrays.javarefbook.model.ModuleModel;
import com.sunrays.javarefbook.model.ProjectModel;
import com.sunrays.javarefbook.model.UserModel;

public class BugCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProjectModel projectModel = new ProjectModel();
		ModuleModel moduleModel = new ModuleModel();
		UserModel userModel = new UserModel();

		try {
			List projectList = projectModel.search();
			List moduleList = moduleModel.search();
			List userList = userModel.search();
			request.setAttribute("projectList", projectList);
			request.setAttribute("moduleList", moduleList);
			request.setAttribute("userList", userList);
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

		BugModel bugModel = new BugModel();
		RequestDispatcher dispatcher = null;

		if ("Get".equals(operation)) {
			try {
				bugModel = bugModel.findByPK(id);
				request.setAttribute("bugModel", bugModel);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			dispatcher = request
					.getRequestDispatcher("BaseLayout.jsp?body=Bug.jsp");
		}

		dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=Bug.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
