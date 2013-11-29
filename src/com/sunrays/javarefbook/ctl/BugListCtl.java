package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrays.javarefbook.model.BugModel;
import com.sunrays.javarefbook.model.ClientModel;

public class BugListCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BugModel bugModel = new BugModel();
		try {
			List bugList = bugModel.search();
			request.setAttribute("bugList", bugList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=BugList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		BugModel bugModel = new BugModel();
		String operation = request.getParameter("operation");
		String[] ids = request.getParameterValues("ids");

		if ("Delete".equals(operation)) {
			for (int i = 0; i < ids.length; i++) {
				try {
					Integer id = Integer.parseInt(ids[i]);
					bugModel.setId(id);
					bugModel.delete();
					request.setAttribute("message", "Bug successfully Deleted.");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Database Error.");
				}
			}
			doGet(request, response);
		}
	}

}
