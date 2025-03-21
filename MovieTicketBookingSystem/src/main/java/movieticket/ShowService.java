package movieticket;

import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ShowService {
    private static final Scanner scanner = new Scanner(System.in);

    // Add a new show
    public static void addShow() {
        System.out.print("Enter movie ID: ");
        int movieId = scanner.nextInt();
        System.out.print("Enter theater ID: ");
        int theaterId = scanner.nextInt();
        System.out.print("Enter show time (YYYY-MM-DD HH:MM:SS): ");
        scanner.nextLine(); // Consume newline
        String showTime = scanner.nextLine();

        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO shows (movie_id, theater_id, show_time) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, movieId);
            statement.setInt(2, theaterId);
            statement.setString(3, showTime);
            statement.executeUpdate();
            System.out.println("Show added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all shows
    public static void viewShows() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM shows";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Shows:");
            while (resultSet.next()) {
                System.out.println("Show ID: " + resultSet.getInt("show_id"));
                System.out.println("Movie ID: " + resultSet.getInt("movie_id"));
                System.out.println("Theater ID: " + resultSet.getInt("theater_id"));
                System.out.println("Show Time: " + resultSet.getString("show_time"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
