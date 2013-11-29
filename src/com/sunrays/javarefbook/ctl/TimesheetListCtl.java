package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunrays.javarefbook.model.TimesheetModel;

public class TimesheetListCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TimesheetModel timesheetModel = new TimesheetModel();
		try {
			List timesheetList = timesheetModel.search();
			request.setAttribute("timesheetList", timesheetList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=TimesheetList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TimesheetModel timesheetModel = new TimesheetModel();
		String operation = request.getParameter("operation");
		String[] ids = request.getParameterValues("ids");

		if ("Delete".equals(operation)) {
			for (int i = 0; i < ids.length; i++) {
				try {
					Integer id = Integer.parseInt(ids[i]);
					timesheetModel.setId(id);
					timesheetModel.delete();
					request.setAttribute("message",
							"Timesheet successfully Deleted.");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Database Error.");
				}
			}
			doGet(request, response);
		}
	}

}
