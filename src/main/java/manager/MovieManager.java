package manager;

import com.sun.media.jfxmedia.control.MediaPlayerOverlay;
import db.DBConnectionProvider;
import model.Genre;
import model.Movie;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MovieManager {
    private Connection connection;

    public MovieManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    public void addMovie(Movie movie) {
        String query = "INSERT INTO movie (title, description, year, genre_id) VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getDescription());
            preparedStatement.setInt(3, movie.getYear());
            preparedStatement.setInt(4, movie.getGenre_id());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                movie.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public List<Movie> getAllMovies() {
        String query = "SELECT * FROM movie";
        try {
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            List<Movie> movies = new LinkedList<Movie>();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setYear(resultSet.getInt(4));
                movies.add(movie);
            }
            return movies;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Movie> getMoviesByGenre(int id) {
        String query = "SELECT * FROM movie\n" +
                "WHERE genre_id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Movie> movies = new ArrayList<Movie>();

            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setYear(resultSet.getInt(4));
                movie.setGenre_id(resultSet.getInt(5));
                movies.add(movie);
                return movies;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    public Movie printMoviesByYear(int year) {
        String query = "SELECT * FROM movie WHERE year = " + year;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                Movie movie = new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setYear(resultSet.getInt(4));
                movie.setGenre_id(resultSet.getInt(5));
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public void deleteMovieByTitle(String delete) {
        String query = "DELETE * FROM movie WHERE title=" + delete;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Movie movie = new Movie();
                movie.setTitle(resultSet.getString(1));
                movie.setDescription(resultSet.getString(2));
                movie.setYear(resultSet.getInt(3));
                movie.setGenre_id(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Movie searchMovieByTitle(String search) {
        String query = "SELECT*FROM movie WHERE title LIKE '" + search + "%'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Movie movie= new Movie();
                movie.setId(resultSet.getInt(1));
                movie.setTitle(resultSet.getString(2));
                movie.setDescription(resultSet.getString(3));
                movie.setYear(resultSet.getInt(4));
                movie.setGenre_id(resultSet.getInt(5));
                return movie;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
