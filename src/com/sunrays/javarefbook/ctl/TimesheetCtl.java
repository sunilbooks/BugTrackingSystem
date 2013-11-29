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

public class TimesheetCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ProjectModel projectModel = new ProjectModel();
		ModuleModel moduleModel = new ModuleModel();

		try {
			List projectList = projectModel.search();
			List moduleList = moduleModel.search();
			request.setAttribute("projectList", projectList);
			request.setAttribute("moduleList", moduleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=Timesheet.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
