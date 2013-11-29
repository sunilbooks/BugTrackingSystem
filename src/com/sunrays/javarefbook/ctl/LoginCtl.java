package com.sunrays.javarefbook.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sunrays.javarefbook.model.UserModel;

public class LoginCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		String userName = request.getParameter("userName");
		String userPassword = request.getParameter("userPassword");
		String operation = request.getParameter("operation");
		RequestDispatcher dispatcher = null;

		UserModel userModel = new UserModel();

		if ("Login".equals(operation)) {
			try {
				userModel.setUserName(userName);
				userModel.setUserPassword(userPassword);
				UserModel dbModel = userModel.authenticate();

				if (dbModel != null) {
					session.setAttribute("user", dbModel);
					response.sendRedirect("DashBoardCtl.do");
				} else {
					request.setAttribute("message",
							"Invalid Username or Password.");
					dispatcher = request.getRequestDispatcher("BaseLayout.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
