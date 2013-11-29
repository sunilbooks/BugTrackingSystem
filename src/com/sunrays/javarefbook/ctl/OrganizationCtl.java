package com.sunrays.javarefbook.ctl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sunrays.javarefbook.model.OrganizationModel;

public class OrganizationCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		OrganizationModel organizationModel = new OrganizationModel();
		String operation = request.getParameter("operation");
		RequestDispatcher dispatcher = null;

		if ("Get".equals(operation)) {
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				organizationModel = organizationModel.findByPK(id);
				request.setAttribute("organizationModel", organizationModel);
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			dispatcher = request
					.getRequestDispatcher("BaseLayout.jsp?body=Organization.jsp");
		}

		dispatcher = request
				.getRequestDispatcher("BaseLayout.jsp?body=Organization.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String faxNo = request.getParameter("faxNo");
		String email = request.getParameter("email");
		String telephonNo = request.getParameter("telephonNo");
		String url = request.getParameter("url");
		String operation = request.getParameter("operation");

		OrganizationModel organizationModel = new OrganizationModel();

		if ("Save".equals(operation)) {
			try {
				organizationModel.setId(organizationModel.nextPK());
				organizationModel.setName(name);
				organizationModel.setAddress(address);
				organizationModel.setFaxNo(faxNo);
				organizationModel.setEmail(email);
				organizationModel.setPhoneNo(telephonNo);
				organizationModel.setUrl(url);
				organizationModel.add();
				request.setAttribute("message",
						"Oraganization successfully Added.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

		if ("Update".equals(operation)) {
			try {
				Integer id = Integer.parseInt(request.getParameter("id"));
				organizationModel.setId(id);
				organizationModel.setName(name);
				organizationModel.setAddress(address);
				organizationModel.setFaxNo(faxNo);
				organizationModel.setEmail(email);
				organizationModel.setPhoneNo(telephonNo);
				organizationModel.setUrl(url);
				organizationModel.update();
				request.setAttribute("message",
						"Oraganization successfully Updated.");
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("errorMessage", "Database Error.");
			}
			doGet(request, response);
		}

	}
}