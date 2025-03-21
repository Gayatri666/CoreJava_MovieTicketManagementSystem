package movieticket;

import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SeatService {
    private static final Scanner scanner = new Scanner(System.in);

   
    public static void checkSeatAvailability(int showId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM seats WHERE show_id = ? AND is_booked = FALSE";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, showId);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Available Seats:");
            while (resultSet.next()) {
                System.out.println("Seat ID: " + resultSet.getInt("seat_id") +
                        ", Seat Number: " + resultSet.getString("seat_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public static boolean bookSeat(int showId, String seatNumber) {
        try (Connection connection = DatabaseConnection.getConnection()) {
           
            String query = "SELECT * FROM seats WHERE show_id = ? AND seat_number = ? AND is_booked = FALSE";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, showId);
            statement.setString(2, seatNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
               
                String updateQuery = "UPDATE seats SET is_booked = TRUE WHERE seat_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setInt(1, resultSet.getInt("seat_id"));
                updateStatement.executeUpdate();
                return true; 
            } else {
                return false;             }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
