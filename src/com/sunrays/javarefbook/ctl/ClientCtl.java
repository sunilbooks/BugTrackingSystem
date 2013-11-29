package com.sunrays.javarefbook.ctl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sunrays.javarefbook.model.ClientModel;

public class ClientCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer id = null;
		String operation = null;
		try {
			id = Integer.parseInt(request.getParameter("id"));
			operation = request.getParameter("operation");
		} catch (NumberFormatException e) {
		}

		ClientModel clientModel = new ClientModel();
		RequestDispatcher dispatcher = null;

		if ("Get".equals(operation)) {
			try {
				clientModel = clientModel.findByPK(id);
				request.setAttribute("clientModel", clientModel);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			dispatcher = request
					.getRequestDispatcher("BaseLayout.jsp?body=Client.jsp");
		}
		dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=Client.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer id = null;
		String name = null;
		String address = null;
		String phoneNo = null;
		String operation = null;
		try {
			name = request.getParameter("name");
			address = request.getParameter("address");
			phoneNo = request.getParameter("phoneNo");
			operation = request.getParameter("operation");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ClientModel clientModel = new ClientModel();

		if ("Save".equals(operation)) {
			try {
				clientModel.setId(clientModel.nextPK());
				clientModel.setName(name);
				clientModel.setAddress(address);
				clientModel.setPhoneNo(phoneNo);
				clientModel.add();
				request.setAttribute("message", "Client successfully Added.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

		if ("Update".equals(operation)) {
			try {
				id = Integer.parseInt(request.getParameter("id"));
				clientModel.setId(id);
				clientModel.setName(name);
				clientModel.setAddress(address);
				clientModel.setPhoneNo(phoneNo);
				clientModel.update();
				request.setAttribute("message", "Client successfully Updated.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

		if ("Delete".equals(operation)) {
			try {
				clientModel.setId(id);
				clientModel.delete();
				request.setAttribute("message", "Client successfully Deleted.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}
	}
}
