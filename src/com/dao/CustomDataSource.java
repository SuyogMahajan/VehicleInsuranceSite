package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.*;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
//
//public class CustomDataSource {
//
//    public static DataSource getDataSource() {
//        SQLServerDataSource ds = new SQLServerDataSource();
//        ds.setServerName("SHAM"); // Replace with your server name
//        ds.setPortNumber(1433);        // Replace with your SQL Server port
//        ds.setDatabaseName("MyPracticeDB");  // Replace with your database name
//        ds.setUser("Suyog");    // Replace with your username
//        ds.setPassword("Hello@123");     // Disable encryption
//        ds.setTrustServerCertificate(true); // Trust the server certificate
//        
//        
//        String connectionUrl = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;encrypt=%b;trustServerCertificate=%b;",
//                ds.getServerName(),
//                ds.getPortNumber(),
//                ds.getDatabaseName(),
//                ds.getUser(),
//                ds.getEncrypt(),
//                ds.getTrustServerCertificate());
//
//        // Print the constructed connection URL
//        System.out.println("Connection URL: " + connectionUrl);
//
//        // Attempt to establish connection (optional)
//        try (var conn = ds.getConnection()) {
//            System.out.println("Connected successfully!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
//        return ds;
//    }
//    
//    public static void main(String[] args) {
////        try (Connection conn = getDataSource().getConnection()) {
////            System.out.println("Connected to the database!");
////        } catch (SQLException e) {
////            e.printStackTrace();
////        };
//    	
////    	try (Connection con = DriverManager.getConnection("jdbc:sqlserver://SHAM:1433;databaseName=MyPracticeDB;encrypt=true;trustServerCertificate=true;");
////){
////						System.out.println("Connected !");
////    	} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//
//    	  String url = "jdbc:sqlserver://SHAM:1433;databaseName=MyPracticeDB;encrypt=false;trustServerCertificate=true;";
//
//          Properties properties = new Properties();
//          properties.setProperty("user", "Suyog");
//          properties.setProperty("password", "Hello@123");
//
//          try (Connection conn = DriverManager.getConnection(url, properties)) {
//              System.out.println("Connected successfully!");
//          } catch (SQLException e) {
//              e.printStackTrace();
//          }
//    	
//    }
//}
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CustomDataSource {
    private static DataSource dataSource;

    static {
       SQLServerDataSource ds = new SQLServerDataSource();
       ds.setServerName("SHAM"); // Replace with your server name
       ds.setPortNumber(1433);        // Replace with your SQL Server port
       ds.setDatabaseName("MyPracticeDB");  // Replace with your database name
       ds.setUser("Suyog");    // Replace with your username
       ds.setPassword("Hello@123");     // Disable encryption
       ds.setTrustServerCertificate(true);
       dataSource = ds;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
