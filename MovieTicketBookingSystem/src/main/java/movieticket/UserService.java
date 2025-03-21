package movieticket;

import com.movieticket.DatabaseConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserService 
{
    private static final Scanner scanner = new Scanner(System.in);

  
    public static void registerUser() 
    {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.executeUpdate();
            
            System.out.println("User registered successfully!");
        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

   
    public static int loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT user_id FROM users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                System.out.println("Login successful!");
                return userId;
            }
            
            else
            {
                System.out.println("Invalid username or password!");
                
                return -1; 
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}