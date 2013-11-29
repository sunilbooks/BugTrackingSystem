package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrays.javarefbook.model.BugModel;

public class DashBoardCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BugModel bugModel = new BugModel();
			bugModel.setStatus("Close");
			List bugList = bugModel.search();
			System.out.println("BugList " + bugList.size());
			request.setAttribute("closeBugList", bugList);

			bugModel.setStatus("Open");
			bugList = bugModel.search();
			request.setAttribute("openBugList", bugList);

			bugModel.setStatus("Fixed");
			bugList = bugModel.search();
			request.setAttribute("fixedBugList", bugList);

			bugModel = new BugModel();
			bugList = bugModel.search();
			request.setAttribute("bugList", bugList);

			RequestDispatcher dispatcher = request
					.getRequestDispatcher("BaseLayout.jsp?body=DashBoard.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
