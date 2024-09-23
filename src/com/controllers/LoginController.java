package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.dao.AdminOperations;
import com.dao.UnderWriterOperations;
import com.model.Admin;
import com.model.UnderWriter;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

@WebServlet("/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String admin_login = request.getParameter("login-type");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println(admin_login);

		RequestDispatcher rd;

		if (admin_login.equals("admin")) {

			Admin ad = AdminOperations.loginAdmin(username, password);

			if (ad != null) {
				
				HttpSession session = request.getSession();
				session.removeAttribute("LOGGED_IN_UNDERWRITER");
				session.setAttribute("LOGGED_IN_ADMIN", ad);
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				
				Cookie userName = new Cookie("LOGGED_IN_ADMIN",ad.getUser_name());
				userName.setMaxAge(30*60);
				
				response.addCookie(userName);
				rd = request.getRequestDispatcher("/admin_home.jsp");
				
			} else {
					rd = getServletContext().getRequestDispatcher("/login.jsp");
			}

		} else {

			UnderWriter underWriter = UnderWriterOperations.loginUnderWriter(username, password);

			if (underWriter != null) {
				
				
				HttpSession session = request.getSession();
				session.removeAttribute("LOGGED_IN_ADMIN");
				session.setAttribute("LOGGED_IN_UNDERWRITER", underWriter);
				//setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30*60);
				
				Cookie userName = new Cookie("LOGGED_IN_UNDERWRITER",underWriter.getUsername());
				userName.setMaxAge(30*60);
				
				response.addCookie(userName);
				rd = request.getRequestDispatcher("/underwriter_home.jsp");
			
			} else {
				rd = getServletContext().getRequestDispatcher("/login.jsp");
			}

		}

		rd.forward(request, response);
	}

}
