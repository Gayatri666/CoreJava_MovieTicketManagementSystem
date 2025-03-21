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
    	           
    	            Class.forName("org.postgresql.Driver");
    	           
    	            return DriverManager.getConnection(URL, USER, PASSWORD);
    	        } catch (ClassNotFoundException | SQLException e) {
    	            e.printStackTrace();
    	            return null; 
    	        }
    	    }
    	}
