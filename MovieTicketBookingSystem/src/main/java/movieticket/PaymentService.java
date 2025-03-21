package movieticket;
import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentService {
   
	
	
    public static boolean processPayment(int bookingId, double amount) {
        try (Connection connection = DatabaseConnection.getConnection()) {
              
            String query = "INSERT INTO payments (booking_id, amount) VALUES (?, ?)";
            
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookingId);
            statement.setDouble(2, amount);
            
            statement.executeUpdate();
            return true; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
