package movieticket;
import com.movieticket.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieService {
    public static void showAvailableMovies() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM movies";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("Movie ID: " + resultSet.getInt("movie_id"));
                System.out.println("Title: " + resultSet.getString("title"));
                System.out.println("Duration: " + resultSet.getInt("duration"));
                System.out.println("Genre: " + resultSet.getString("genre"));
                System.out.println("-----------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
