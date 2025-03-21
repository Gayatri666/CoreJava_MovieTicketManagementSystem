package movieticket;

import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookingService 
{
    private static final Scanner scanner = new Scanner(System.in);

    public static void bookTicket() 
    {
        System.out.print("Enter show ID which you want: ");
        int showId = scanner.nextInt();
        
        
        System.out.print("Enter seat number: ");
        String seatNumber = scanner.next();

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
                System.out.println("Seat booked ");
            } else {
                System.out.println("Seat not available!!!!!!!!!!!!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
