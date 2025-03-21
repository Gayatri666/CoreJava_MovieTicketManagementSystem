package movieticket;

import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportService {
  
    public static void generateBookingReport() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM bookings";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Booking Report:");
             while (resultSet.next()) {
                System.out.println("Booking ID: " + resultSet.getInt("booking_id"));
                System.out.println("User ID: " + resultSet.getInt("user_id"));
                System.out.println("Show ID: " + resultSet.getInt("show_id"));
                System.out.println("Seat ID: " + resultSet.getInt("seat_id"));
                System.out.println("Booking Time: " + resultSet.getString("booking_time"));
                System.out.println("********************");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public static void generatePaymentReport() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM payments";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Payment Report:");
            while (resultSet.next()) {
                System.out.println("Payment ID: " + resultSet.getInt("payment_id"));
                System.out.println("Booking ID: " + resultSet.getInt("booking_id"));
                System.out.println("Amount: " + resultSet.getDouble("amount"));
                System.out.println("Payment Time: " + resultSet.getString("payment_time"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public static void generateLoyaltyReport() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT user_id, loyalty_points FROM users";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Loyalty Points Report:");
            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getInt("user_id"));
                System.out.println("Loyalty Points: " + resultSet.getInt("loyalty_points"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}