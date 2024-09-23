package com.dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.controllers.Constants;
import com.custom_exceptions.InvalidAdminLogInException;
import com.custom_exceptions.InvalidUnderWriterLogInException;
import com.model.*;

public class InsuranceOperations {

	private static Connection con = ConnectionProvider.getCon();

	// list
	public static ArrayList<Insurance> listInsurances(Admin LOGGED_IN_ADMIN) throws InvalidAdminLogInException {
		ArrayList<Insurance> list = null;

		if (LOGGED_IN_ADMIN != null) {

			list = new ArrayList<>();
			String query = "SELECT * FROM " + Constants.INS_DB_NAME;

			try {

				Statement stmt = con.createStatement();
				ResultSet res = stmt.executeQuery(query);

				while (res.next()) {

					int underwriter_id = res.getInt("UId");
					int policyNo = res.getInt("PolicyNo");
					String vehicleNo = res.getString("VehicleNo");
					String vehicleType = res.getString("VehicleType");
					String customerName = res.getString("CustomerName");
					int engineNo = res.getInt("EngineNo");
					int chasisNo = res.getInt("ChasisNo");
					String type = res.getString("Type");
					Long phoneNo = Long.parseLong(res.getString(Constants.INS_PHONENO));
					double premiumAmnt = res.getDouble("PremiumAmnt");
					Date fromDate = res.getDate("FromDate");
					Date toDate = res.getDate("TooDate");

					Insurance v = new Insurance(underwriter_id, policyNo, vehicleNo, vehicleType, customerName,
							engineNo, chasisNo, type, phoneNo, premiumAmnt, fromDate, toDate);
					list.add(v);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidAdminLogInException();
		}
		return list;
	}

	public static Insurance getInsuranceById(int id, UnderWriter LOGGED_IN_UNDERWRITER) throws InvalidUnderWriterLogInException {

		PreparedStatement statement = null;
		Insurance insurance = null;
		
		if (LOGGED_IN_UNDERWRITER != null) {

			try {

				String query = "SELECT * FROM " + Constants.INS_DB_NAME + " WHERE " + Constants.INS_POLICYNO
						+ " = ? and " + Constants.INS_UW_ID + " = ?";

				statement = con.prepareStatement(query);
				statement.setInt(1, id);
				statement.setInt(2, LOGGED_IN_UNDERWRITER.getId());

				ResultSet res = statement.executeQuery();
				if (res.next()) {

					int underwriter_id = res.getInt(Constants.INS_UW_ID);
					int policyNo = res.getInt(Constants.INS_POLICYNO);
					String vehicleNo = res.getString(Constants.INS_VEHICLENO);
					String vehicleType = res.getString(Constants.INS_VEHICLETYPE);
					String customerName = res.getString(Constants.INS_CUSTOMERNAME);
					int engineNo = res.getInt(Constants.INS_ENGINENO);
					int chasisNo = res.getInt(Constants.INS_CHASSISNO);
					String type = res.getString(Constants.INS_TYPE);
					Long phoneNo = Long.parseLong(res.getString(Constants.INS_PHONENO));
					double premiumAmnt = res.getDouble(Constants.INS_PREMIUMAMT);
					Date fromDate = res.getDate(Constants.INS_FROMDATE);
					Date toDate = res.getDate(Constants.INS_TODATE);

					insurance = new Insurance(underwriter_id, policyNo, vehicleNo, vehicleType, customerName, engineNo,
							chasisNo, type, phoneNo, premiumAmnt, fromDate, toDate);
					
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidUnderWriterLogInException();
		}

		return insurance;
	}
	
	public static Insurance getInsuranceByVehicleNo(String vehicleNo, UnderWriter LOGGED_IN_UNDERWRITER) throws InvalidUnderWriterLogInException {

		PreparedStatement statement = null;
		Insurance insurance = null;
		if (LOGGED_IN_UNDERWRITER != null) {

			try {

				String query = "SELECT * FROM " + Constants.INS_DB_NAME + " WHERE " + Constants.INS_VEHICLENO
						+ " = ? and " + Constants.INS_UW_ID + " = ?";

				statement = con.prepareStatement(query);
				statement.setString(1, vehicleNo);
				statement.setInt(2,LOGGED_IN_UNDERWRITER.getId());

				ResultSet res = statement.executeQuery();
				if (res.next()) {

					int underwriter_id = res.getInt(Constants.INS_UW_ID);
					int policyNo = res.getInt(Constants.INS_POLICYNO);
					String vehicleType = res.getString(Constants.INS_VEHICLETYPE);
					String customerName = res.getString(Constants.INS_CUSTOMERNAME);
					int engineNo = res.getInt(Constants.INS_ENGINENO);
					int chasisNo = res.getInt(Constants.INS_CHASSISNO);
					String type = res.getString(Constants.INS_TYPE);
					Long phoneNo = Long.parseLong(res.getString(Constants.INS_PHONENO));
					double premiumAmnt = res.getDouble(Constants.INS_PREMIUMAMT);
					Date fromDate = res.getDate(Constants.INS_FROMDATE);
					Date toDate = res.getDate(Constants.INS_TODATE);

					insurance = new Insurance(underwriter_id, policyNo, vehicleNo, vehicleType, customerName, engineNo,
							chasisNo, type, phoneNo, premiumAmnt, fromDate, toDate);

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidUnderWriterLogInException();
		}

		return insurance;
	}
	
	public static ArrayList<Insurance> listByUser(int uid, Admin LOGGED_IN_ADMIN) throws InvalidAdminLogInException {

		if (LOGGED_IN_ADMIN != null) {

			ArrayList<Insurance> list = new ArrayList<>();
			String query = "SELECT * FROM " + Constants.INS_DB_NAME + " WHERE " + Constants.INS_UW_ID + " = ?";

			try {

				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, uid);
				ResultSet res = stmt.executeQuery();

				while (res.next()) {

					int underwriter_id = res.getInt(Constants.INS_UW_ID);
					int policyNo = res.getInt(Constants.INS_POLICYNO);
					String vehicleNo = res.getString(Constants.INS_VEHICLENO);
					String vehicleType = res.getString(Constants.INS_VEHICLETYPE);
					String customerName = res.getString(Constants.INS_CUSTOMERNAME);
					int engineNo = res.getInt(Constants.INS_ENGINENO);
					int chasisNo = res.getInt(Constants.INS_CHASSISNO);
					String type = res.getString(Constants.INS_TYPE);
					Long phoneNo = Long.parseLong(res.getString(Constants.INS_PHONENO));
					double premiumAmnt = res.getDouble(Constants.INS_PREMIUMAMT);
					Date fromDate = res.getDate(Constants.INS_FROMDATE);
					Date toDate = res.getDate(Constants.INS_TODATE);

					Insurance v = new Insurance(underwriter_id, policyNo, vehicleNo, vehicleType, customerName,
							engineNo, chasisNo, type, phoneNo, premiumAmnt, fromDate, toDate);
					list.add(v);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return list;
		} else {
			throw new InvalidAdminLogInException();
		}

	}

	// insert
	public static int insertInsurance(Insurance v, UnderWriter LOGGED_IN_UNDERWRITER) throws InvalidUnderWriterLogInException {
		if (LOGGED_IN_UNDERWRITER != null) {
			String query = "INSERT INTO " + Constants.INS_DB_NAME + "(" + Constants.INS_VEHICLENO + ","
					+ Constants.INS_VEHICLETYPE + "," + Constants.INS_CUSTOMERNAME + "," + Constants.INS_ENGINENO + ","
					+ Constants.INS_CHASSISNO + "," + Constants.INS_PHONENO + "," + Constants.INS_TYPE + ","
					+ Constants.INS_PREMIUMAMT + "," + Constants.INS_FROMDATE + "," + Constants.INS_TODATE + ","
					+ Constants.INS_UW_ID + ")" + " VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? )";

			try {

				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, v.getVehicleNo());
				stmt.setString(2, v.getVehicleType());
				stmt.setString(3, v.getCustomerName());
				stmt.setInt(4, v.getEngineNo());
				stmt.setInt(5, v.getChasisNo());
				stmt.setLong(6, v.getPhoneNo());
				stmt.setString(7, v.getType());
				stmt.setDouble(8, v.getPremiumAmnt());
				stmt.setDate(9, v.getFromDate());
				stmt.setDate(10, v.getToDate());
				stmt.setInt(11, v.getUnderwriter_id());

				int r = stmt.executeUpdate();

				return r;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			throw new InvalidUnderWriterLogInException();
		}
		return 0;
	}

	// update
	public static int renewInsurance(Insurance t, UnderWriter LOGGED_IN_UNDERWRITER) throws InvalidUnderWriterLogInException {

		if (LOGGED_IN_UNDERWRITER != null) {
			Insurance v = new Insurance(t);

			Date today = Date.valueOf(LocalDate.now());

			if (v.getToDate().before(today)) {

				v.setFromDate(today);
				v.setToDate(Date.valueOf(LocalDate.now().plusYears(1)));
				System.out.println(v);
				insertInsurance(v, LOGGED_IN_UNDERWRITER);

			} else {

				v.setFromDate(Date.valueOf(v.getToDate().toLocalDate().plusDays(1)));
				v.setToDate(Date.valueOf(v.getFromDate().toLocalDate().plusYears(1)));

			}

			return insertInsurance(v, LOGGED_IN_UNDERWRITER);
		} else {
			throw new InvalidUnderWriterLogInException();
		}
	}

	// delete
	public static int removeInsurance(int vid, UnderWriter LOGGED_IN_UNDERWRITER) throws InvalidUnderWriterLogInException {

		PreparedStatement preparedStatement = null;
		int result = 0;

		if (LOGGED_IN_UNDERWRITER != null) {

			try {

				String query = "DELETE FROM " + Constants.INS_DB_NAME + " WHERE " + Constants.INS_POLICYNO + " = ? and "
						+ Constants.INS_UW_ID + " = ?";

				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, vid);
				preparedStatement.setInt(2, LOGGED_IN_UNDERWRITER.getId());

				result = preparedStatement.executeUpdate();
				preparedStatement.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return result;
		} else {
			throw new InvalidUnderWriterLogInException();
		}

	}

	public static int upgradeInsurance(Insurance v, UnderWriter LOGGED_IN_UNDERWRITER) throws InvalidUnderWriterLogInException {

		if (LOGGED_IN_UNDERWRITER != null) {
			
			if (v.getType().equals("Full Insurance")) {

				PreparedStatement preparedStatement = null;
				int result = 0;

				try {

					String query = "UPDATE " + Constants.INS_DB_NAME + " SET " + Constants.INS_TYPE + " = ? WHERE "
							+ Constants.INS_POLICYNO + " = ?";
					System.out.println(query);
					preparedStatement = con.prepareStatement(query);
					preparedStatement.setString(1, "ThirdParty");
					preparedStatement.setInt(2, v.getPolicyNo());
					
					result = preparedStatement.executeUpdate();
					preparedStatement.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}

				return result;
			}
			return 0;
		} else {
			throw new InvalidUnderWriterLogInException();
		}
	}

}
