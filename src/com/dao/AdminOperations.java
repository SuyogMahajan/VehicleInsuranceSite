package com.dao;

import java.sql.*;

import com.controllers.Constants;
import com.model.Admin;

public class AdminOperations {
	private static Connection con = ConnectionProvider.getCon();
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		con.close();
	}

	public static Admin loginAdmin(String username, String password) {
		PreparedStatement statement = null;
		Admin admin = null;
		
		try {

			String query = "SELECT * FROM " + Constants.ADMIN_DB_NAME + " WHERE " + Constants.ADMIN_NAME + " = ?"
					+ " and " + Constants.ADMIN_PASSWORD + " = ?";
			
			statement = con.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();

			if (result.next()) {
				String u_name = result.getString(Constants.ADMIN_NAME);
				String p_word = result.getString(Constants.ADMIN_PASSWORD);

				admin = new Admin(u_name, p_word);
				return admin;
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return admin;
	}
}
