package movieticket;

import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TheaterService 
{
    private static final Scanner scanner = new Scanner(System.in);

   
    public static void addTheater() 
    {
    	 
          System.out.print("Enter theater name: ");
        String name = scanner.nextLine();
        
        
        System.out.print("Enter theater location: ");
        String location = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String query = "INSERT INTO theaters (name, location) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, location);
            statement.executeUpdate();
            
            System.out.println("Theater added successfully!");
            
            
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }

   
    public static void viewTheaters() 
    {
        try (Connection connection = DatabaseConnection.getConnection()) 
        {
            String query = "SELECT * FROM theaters";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Theaters:");
            while (resultSet.next())
            {
            	
            	
                System.out.println("Theater ID: " + resultSet.getInt("theater_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Location: " + resultSet.getString("location"));
                System.out.println("-----------------------------");
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
