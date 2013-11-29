package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunrays.javarefbook.model.UserModel;

public class UserListCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserModel userModel = new UserModel();

		try {
			List userList = userModel.search();
			request.setAttribute("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=UserList.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		UserModel userModel = new UserModel();
		String operation = request.getParameter("operation");
		String[] ids = request.getParameterValues("ids");

		if ("Delete".equals(operation)) {
			for (int i = 0; i < ids.length; i++) {
				try {
					Integer id = Integer.parseInt(ids[i]);
					userModel.setId(id);
					userModel.delete();
					request.setAttribute("message",
							"User successfully Deleted.");
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Database Error.");
				}
			}
			doGet(request, response);
		}
	}
}