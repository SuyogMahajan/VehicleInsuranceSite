package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.controllers.Constants;

public class ConnectionProvider {
	
	private static Connection con = null;
	
	static {
		try {
			con = CustomDataSource.getConnection();
			System.out.println("Conneccted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static Connection getCon() {
		return con;
	}

}
