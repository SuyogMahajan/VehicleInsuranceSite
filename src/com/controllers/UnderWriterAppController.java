package com.controllers;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.custom_exceptions.InvalidUnderWriterLogInException;
import com.dao.InsuranceOperations;
import com.dao.UnderWriterOperations;
import com.model.Insurance;
import com.model.UnderWriter;

@WebServlet("/underwriter_app/*")
public class UnderWriterAppController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private InsuranceOperations insuranceOperations = new InsuranceOperations();

	public UnderWriterAppController() {
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
		
		case "/insertInsurance": {
			insertInsurance(request, response);
			break;
		}
		case "/renewInsurance": {
			renewInsurance(request, response);
			break;
		}
		case "/changeInsurance": {
			changeInsurance(request, response);
			break;
		}
		case "/viewInsurance": {
			viewInsurance(request, response);
			break;
		}
		default: {
			break;
		}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	// System.out.println("1. Create a new Vehicle Insurance");
	private void insertInsurance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			int underwriter_id = Integer.parseInt(request.getParameter("underwriter-id"));
			String VehicleNo = request.getParameter("vehicle-no");
			String VehicleType = request.getParameter("vehicle-type");
			String CustomerName = request.getParameter("customer-name");
			int EngineNo = Integer.parseInt(request.getParameter("engine-no"));
			int ChasisNo = Integer.parseInt(request.getParameter("chasis-no"));;
			String Type = request.getParameter("type");
			double price = Double.parseDouble(request.getParameter("premium-amount"));
			Long PhoneNo = Long.parseLong(request.getParameter("phone-no"));
			Date FromDate = Date.valueOf(request.getParameter("from-date"));
			
			
			Insurance ins = new Insurance(underwriter_id, VehicleNo, VehicleType, CustomerName, EngineNo, ChasisNo,
					Type, PhoneNo, FromDate);
			
			UnderWriter LOGGED_IN_UNDERWRITER = (UnderWriter) (request.getSession().getAttribute("LOGGED_IN_UNDERWRITER"));
			int res = InsuranceOperations.insertInsurance(ins, LOGGED_IN_UNDERWRITER);
			
			if(res > 0) {
				request.setAttribute("SUCCESS_MESSAGE", "Policy Created Successfully !");
			}else{
				request.setAttribute("FAILURE_MESSAGE", "Something went wrong, sorry !");
			}
			
			System.out.println("Insurance" + res);

		} catch (Exception e) {
			request.setAttribute("FAILURE_MESSAGE",e.getMessage());
			e.printStackTrace();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("/create_vehicle_insurance.jsp");
			rd.forward(request, response);
		}

	}

	// System.out.println("2. Renewal of a Policy");

	private void renewInsurance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		if(request.getParameter("policy-id") != null) {
			String policyId = request.getParameter("policy-id");
			System.out.println(policyId);
			try {
				UnderWriter LOGGED_IN_UNDERWRITER = (UnderWriter) (request.getSession().getAttribute("LOGGED_IN_UNDERWRITER"));
				
				Insurance u = InsuranceOperations.getInsuranceById(Integer.parseInt(policyId), LOGGED_IN_UNDERWRITER);
				if(u != null) { 
				request.setAttribute("Ins", u);
				}else{
					request.setAttribute("FAILURE_MESSAGE", "Something went wrong, sorry !");
				}
			} catch (Exception e) {
				request.setAttribute("FAILURE_MESSAGE", "Something went wrong, sorry !");
				System.out.println(e.getMessage());
			}
		}
		
		if(request.getParameter("confirm-renewal") != null) {
			String policyId = request.getParameter("policy-id");
			Insurance u;
			try {
				UnderWriter LOGGED_IN_UNDERWRITER = (UnderWriter) (request.getSession().getAttribute("LOGGED_IN_UNDERWRITER"));
				
				u = InsuranceOperations.getInsuranceById(Integer.parseInt(policyId), LOGGED_IN_UNDERWRITER);
				int row_aff = InsuranceOperations.renewInsurance(u, LOGGED_IN_UNDERWRITER);
				
				if(row_aff > 0) {
					request.setAttribute("SUCCESS_MESSAGE", "Policy Renewal Successfull !");
				}else{
					request.setAttribute("FAILURE_MESSAGE", "Something went wrong, sorry !");
				}
			} catch (Exception e) {
				request.setAttribute("FAILURE_MESSAGE", "Something went wrong, sorry !");
				e.printStackTrace();
			} 
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/renew_policy.jsp");
		rd.forward(request, response);

	}

	// System.out.println("3. Changing of a policy");

	private void changeInsurance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("policy-id") != null && request.getParameter("policy-type") != null) {
			
			int pid = Integer.parseInt(request.getParameter("policy-id"));
			String type = request.getParameter("policy-type");

			try {
				UnderWriter LOGGED_IN_UNDERWRITER = (UnderWriter) (request.getSession().getAttribute("LOGGED_IN_UNDERWRITER"));
				
				Insurance ins = InsuranceOperations.getInsuranceById(pid, LOGGED_IN_UNDERWRITER);
				int res = InsuranceOperations.upgradeInsurance(ins, LOGGED_IN_UNDERWRITER);
				
				if(res > 0) {
					request.setAttribute("SUCCESS_MESSAGE", "Succefully updated type to Third Party");
				}else{
					request.setAttribute("FAILURE_MESSAGE", "There's no provision to update the policy type from Third party to full Insurance.");
				}

			} catch (InvalidUnderWriterLogInException e) {
				e.printStackTrace();
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher("/change_policy.jsp");
		rd.forward(request, response);

	}

	// System.out.println("4. View Policy");
	private void viewInsurance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("vehicle-no") != null && !request.getParameter("vehicle-no").isEmpty()) {
			String vehicleNo = request.getParameter("vehicle-no");

			try {
				UnderWriter LOGGED_IN_UNDERWRITER = (UnderWriter) (request.getSession().getAttribute("LOGGED_IN_UNDERWRITER"));
				
				Insurance u = InsuranceOperations.getInsuranceByVehicleNo(vehicleNo, LOGGED_IN_UNDERWRITER);
				if(u == null) {
					request.setAttribute("FAILURE_MESSAGE","Insurance Not Found");
				}else{
					request.setAttribute("Ins", u);
				}
			} catch (Exception e) {
				request.setAttribute("FAILURE_MESSAGE",e.getMessage());
				System.out.println(e.getMessage());
			}

		}

		if (request.getParameter("policy-id") != null && !request.getParameter("policy-id").isEmpty()) {
			String policyId = request.getParameter("policy-id");

			try {
				UnderWriter LOGGED_IN_UNDERWRITER = (UnderWriter) (request.getSession().getAttribute("LOGGED_IN_UNDERWRITER"));
				
				Insurance u = InsuranceOperations.getInsuranceById(Integer.parseInt(policyId), LOGGED_IN_UNDERWRITER);
				if(u == null) {
					request.setAttribute("FAILURE_MESSAGE","Insurance Not Found");
				}else{
				request.setAttribute("Ins", u);
				}
			} catch (Exception e) {
				request.setAttribute("FAILURE_MESSAGE",e.getMessage());
				System.out.println(e.getMessage());
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("/view_policy.jsp");
		rd.forward(request, response);
	}
	// System.out.println("5. Exit Menu");

}
