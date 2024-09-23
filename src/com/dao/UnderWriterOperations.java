package com.dao;

import java.sql.*;
import java.util.ArrayList;

import com.controllers.Constants;
import com.custom_exceptions.InvalidAdminLogInException;
import com.model.Admin;
import com.model.UnderWriter;

public class UnderWriterOperations {

	private static Connection con =  ConnectionProvider.getCon();

	public static UnderWriter loginUnderWriter(String username, String password) {
		PreparedStatement statement = null;
		UnderWriter underwriter = null;

		try {

			String query = "SELECT * FROM " + Constants.UW_DB_NAME + " WHERE " + Constants.UW_NAME + " = ?" + " and "
					+ Constants.UW_PASSWORD + " = ? ";

			statement = con.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				int id_temp = result.getInt(Constants.UW_ID);
				String u_name = result.getString(Constants.UW_NAME);
				String p_word = result.getString(Constants.UW_PASSWORD);
				Date dob = result.getDate(Constants.UW_DOB);
				Date doj = result.getDate(Constants.UW_DOJ);

				underwriter = new UnderWriter(id_temp, u_name, p_word, dob, doj);
				return underwriter;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static int insertUnderWriter(UnderWriter underWriter, Admin LOGGED_IN_ADMIN) throws InvalidAdminLogInException {
		
		if(LOGGED_IN_ADMIN != null) {
		PreparedStatement preparedStatement = null;
		int rowAffected = 0;

		try {

			String insert = "INSERT INTO " + Constants.UW_DB_NAME + "(" + Constants.UW_NAME + "," + Constants.UW_DOB
					+ "," + Constants.UW_DOJ + "," + Constants.UW_PASSWORD + ") values(?,?,?,?)";

			preparedStatement = con.prepareStatement(insert);

			preparedStatement.setString(1, underWriter.getUsername());
			preparedStatement.setDate(2, underWriter.getDateOfBirth());
			preparedStatement.setDate(3, underWriter.getDateOfJoining());
			preparedStatement.setString(4, underWriter.getPassword());

			rowAffected = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
		}else{
			throw new InvalidAdminLogInException();
		}
	}

	public static ArrayList<UnderWriter> listUnderWriter(Admin LOGGED_IN_ADMIN) throws InvalidAdminLogInException {
		Statement statement = null;
		ArrayList<UnderWriter> uw_list = new ArrayList<>();
		
		if(LOGGED_IN_ADMIN != null){
		String query = "SELECT * FROM " + Constants.UW_DB_NAME;

		try {
			statement = con.createStatement();

			ResultSet result = statement.executeQuery(query);

			while (result.next()) {

				int id = result.getInt(Constants.UW_ID);
				String u_name = result.getString(Constants.UW_NAME);
				String p_word = result.getString(Constants.UW_PASSWORD);
				Date dob = result.getDate(Constants.UW_DOB);
				Date doj = result.getDate(Constants.UW_DOJ);

				UnderWriter u = new UnderWriter(id, u_name, p_word, dob, doj);
				uw_list.add(u);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uw_list;
		}else{
			throw new InvalidAdminLogInException();
		}

	}

	public static UnderWriter searchUnderWirter(int id,Admin LOGGED_IN_ADMIN) throws InvalidAdminLogInException {

		PreparedStatement statement = null;
		UnderWriter underwriter = null;
		if(LOGGED_IN_ADMIN != null) {
		try {

			String query = "SELECT * FROM " + Constants.UW_DB_NAME + " WHERE " + Constants.UW_ID + " = ?";

			statement = con.prepareStatement(query);
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();

			while (result.next()) {
				int id_temp = result.getInt(Constants.UW_ID);
				String u_name = result.getString(Constants.UW_NAME);
				String p_word = result.getString(Constants.UW_PASSWORD);
				Date dob = result.getDate(Constants.UW_DOB);
				Date doj = result.getDate(Constants.UW_DOJ);

				underwriter = new UnderWriter(id_temp, u_name, p_word, dob, doj);
				return underwriter;
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
		}else{
			throw new InvalidAdminLogInException();
		}
	}

	public static int updateUnderWriter(int id, String pass, Admin LOGGED_IN_ADMIN) throws InvalidAdminLogInException {

		int rowAffected = 0;
		PreparedStatement preparedStatement = null;
		String query = "UPDATE " + Constants.UW_DB_NAME + " SET " + Constants.UW_PASSWORD + " = ? WHERE "
				+ Constants.UW_ID + " = ?";
		
		if(LOGGED_IN_ADMIN != null) {
		try {

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, pass);

			preparedStatement.setInt(2, id);
			rowAffected = preparedStatement.executeUpdate();

			return rowAffected;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
		
		}else{
			throw new InvalidAdminLogInException();
		}
	}

	public static int deleteUnderwriter(int id, Admin LOGGED_IN_ADMIN) throws InvalidAdminLogInException {
		PreparedStatement preparedStatement = null;
		int result = 0;
		
		if(LOGGED_IN_ADMIN != null) {
		try {

			String query = "DELETE FROM " + Constants.UW_DB_NAME + " WHERE " + Constants.UW_ID + " = ?";

			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
		}else{
			throw new InvalidAdminLogInException();
		}
	}

}
