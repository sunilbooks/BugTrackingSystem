package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrays.javarefbook.model.TimesheetModel;

public class TimesheetDashBoardCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		TimesheetModel timesheetModel = new TimesheetModel();
		try {
			timesheetModel.setAccepted("No");
			List timesheetList = timesheetModel.search();
			request.setAttribute("rejectedTmsList", timesheetList);

			timesheetModel.setStatus("Submit");
			timesheetModel.setAccepted("Not Attempted");
			timesheetList = timesheetModel.search();
			request.setAttribute("waitedTmsList", timesheetList);

			timesheetModel.setStatus("Save");
			timesheetModel.setAccepted("Not Attempted");
			timesheetList = timesheetModel.search();
			request.setAttribute("submittedTmsList", timesheetList);

			timesheetModel.setStatus("Submit");
			timesheetModel.setAccepted("Yes");
			timesheetList = timesheetModel.search();
			request.setAttribute("approvedTmsList", timesheetList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=TimesheetDashBoard.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
