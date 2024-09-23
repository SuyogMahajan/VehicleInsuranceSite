package com.controllers;

import java.util.*;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.custom_exceptions.InvalidAdminLogInException;
import com.dao.AdminOperations;
import com.dao.InsuranceOperations;
import com.dao.UnderWriterOperations;
import com.model.Admin;
import com.model.Insurance;
import com.model.UnderWriter;

@WebServlet("/admin_app/*")
public class AdminAppController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAppController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getPathInfo();
		System.out.println(action);

		switch (action) {
		
		case "/logout" : {
			HttpSession session = request.getSession();
			session.invalidate();
			RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		
		case "/insertUnderWriter": {
			insertUnderWriter(request, response);
			break;
		}
		case "/searchUnderWriter": {
			searchUnderWriter(request, response);
			break;
		}
		case "/updateUnderWriter": {
			updateUnderWriter(request, response);
			break;
		}
		case "/deleteUnderwriter": {
			deleteUnderwriter(request, response);
			break;
		}
		case "/viewAllUnderwriters": {
			viewAllUnderwriters(request, response);
			break;
		}
		 case "/viewUnderwriterVehicles": {
		 viewUnderwriterVehicles(request, response);
		 break;
		 }

		default: {
			break;
		}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("action");
		doGet(request, response);
	}

	private void showSpecVehPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/view_specific_underwriter_vehicle.jsp");
		rd.forward(request, response);
	}

	// System.out.println("1. Register Underwriter");
	private void insertUnderWriter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			String name = request.getParameter("name");
			Date dob = Date.valueOf(request.getParameter("dob"));
			Date doj = Date.valueOf(request.getParameter("doj"));
			UnderWriter underWriter = new UnderWriter(name, dob, doj);

			Admin LOGGED_IN_ADMIN = (Admin)(request.getSession().getAttribute("LOGGED_IN_ADMIN"));
			
			
			int r = UnderWriterOperations.insertUnderWriter(underWriter, LOGGED_IN_ADMIN);
		} catch (Exception e) {
			
		}finally {
			RequestDispatcher rd = request.getRequestDispatcher("/underwriter_registration.jsp");
			rd.forward(request, response);
		}

	}

	// System.out.println("2. Search Underwriter by ID");
	private void searchUnderWriter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("underwriter-id") != null) {
			String id = request.getParameter("underwriter-id");

			Admin LOGGED_IN_ADMIN = (Admin)(request.getSession().getAttribute("LOGGED_IN_ADMIN"));
		
			try {
				UnderWriter u = UnderWriterOperations.searchUnderWirter(Integer.parseInt(id), LOGGED_IN_ADMIN);
				request.setAttribute("Und", u);
			} catch (InvalidAdminLogInException e) {
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher("/search_underwriter.jsp");
		rd.forward(request, response);

	}

	// System.out.println("3. Update Underwriter");
	private void updateUnderWriter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			Admin LOGGED_IN_ADMIN = (Admin)(request.getSession().getAttribute("LOGGED_IN_ADMIN"));
			if (request.getParameter("un_id") != null && request.getParameter("newPassword") == null 
					&& request.getParameter("confirmPassword") == null) {

				String un_id = request.getParameter("un_id");
				
				UnderWriter u = UnderWriterOperations.searchUnderWirter(Integer.parseInt(un_id), LOGGED_IN_ADMIN);

				if (u != null) {
					request.setAttribute("found_id", un_id);
					request.setAttribute("ID_NOT_FOUND", 0);
				}
				
			} else {
				
				if (request.getParameter("un_id") != null &&
						request.getParameter("newPassword") != null 
						&& request.getParameter("confirmPassword") != null) {
					
					int found_id = Integer.parseInt(request.getParameter("un_id"));
					String new_pass = request.getParameter("newPassword");
					String conf_pass = request.getParameter("confirmPassword");
					
					System.out.println(found_id + " " + new_pass + " " + conf_pass);
					
					if(new_pass.equals(conf_pass)) {
						int res = UnderWriterOperations.updateUnderWriter(found_id, new_pass,LOGGED_IN_ADMIN);
						System.out.println(res);
					}
					
				} 
			}
		} catch (Exception e) {
e.printStackTrace();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("/update_password.jsp");
			rd.forward(request, response);
		}
	}

	// System.out.println("4. Delete Underwriter");
	private void deleteUnderwriter(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Admin LOGGED_IN_ADMIN = (Admin)(request.getSession().getAttribute("LOGGED_IN_ADMIN"));
			int id = Integer.parseInt(request.getParameter("underwriter-id"));

			int res = UnderWriterOperations.deleteUnderwriter(id, LOGGED_IN_ADMIN);
			request.setAttribute("res", res);
		} catch (Exception e) {

		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("/delete_underwriter.jsp");
			rd.forward(request, response);
		}
	}

	// System.out.println("5. View All Underwriters");
	private void viewAllUnderwriters(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Admin LOGGED_IN_ADMIN = (Admin)(request.getSession().getAttribute("LOGGED_IN_ADMIN"));
		ArrayList<UnderWriter> l;
		try {
			l = UnderWriterOperations.listUnderWriter(LOGGED_IN_ADMIN);
			request.setAttribute("listUnd", l);
		} catch (InvalidAdminLogInException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/view_all_underwriters.jsp");
		rd.forward(request, response);

	}

	// System.out.println("6. View All Vehicles registered for specific
	// Underwriter");
	private void viewUnderwriterVehicles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("underwriter-id") != null) {
			String id = request.getParameter("underwriter-id");
			Admin LOGGED_IN_ADMIN = (Admin)(request.getSession().getAttribute("LOGGED_IN_ADMIN"));
			UnderWriter u;
			try {
				u = UnderWriterOperations.searchUnderWirter(Integer.parseInt(id), LOGGED_IN_ADMIN);
				if(u != null) {
						List<Insurance> l = InsuranceOperations.listByUser(u.getId(), LOGGED_IN_ADMIN);
						request.setAttribute("listIns", l);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}

		RequestDispatcher rd = request.getRequestDispatcher("/view_specific_underwriter_vehicle.jsp");
		rd.forward(request, response);
	}

	// System.out.println("7. Exit Menu");
}
