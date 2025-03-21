package com.movieticket;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/pdea";
    private static final String USER = "postgres";
    private static final String PASSWORD = "G@m@k@j@123456";

    public static Connection getConnection() throws SQLException {
    	 
    	        try {
    	            // Load the PostgreSQL JDBC driver
    	            Class.forName("org.postgresql.Driver");
    	            // Establish the connection
    	            return DriverManager.getConnection(URL, USER, PASSWORD);
    	        } catch (ClassNotFoundException | SQLException e) {
    	            e.printStackTrace();
    	            return null; // Return null if connection fails
    	        }
    	    }
    	}
