package movieticket;
import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoyaltyService 

{
    
    public static void addLoyaltyPoints(int userId, int points)
    {
        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String query = "UPDATE users SET loyalty_points = loyalty_points + ? WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, points);
            statement.setInt(2, userId);
            statement.executeUpdate();
        }
        catch (SQLException e) 
        {
            
        	e.printStackTrace();
        }
          
    }

    public static boolean redeemLoyaltyPoints(int userId, int points)
    {
        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            
            String query = "SELECT loyalty_points FROM users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	
                int currentPoints = resultSet.getInt("loyalty_points");
                if (currentPoints >= points) 
                {
                    
                    String updateQuery = "UPDATE users SET loyalty_points = loyalty_points - ? WHERE user_id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, points);
                    updateStatement.setInt(2, userId);
                    updateStatement.executeUpdate();
                    return true;
                }
            }
            
            return false; 
            
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
            return false;
        }
    }

    
    
    
    public static int getLoyaltyPoints(int userId) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT loyalty_points FROM users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("loyalty_points");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
