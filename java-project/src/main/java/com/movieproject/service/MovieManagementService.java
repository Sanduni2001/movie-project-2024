package com.movieproject.service;

import com.movieproject.model.Movie;
import com.movieproject.model.ShowDate;
import com.movieproject.model.ShowTime;
import com.movieproject.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieManagementService {

    // Add a new movie
    public boolean addMovie(Movie movie) {
        String query = "INSERT INTO Movies (movieName, description, releaseDate, directorName,musicBy,produceBy, ticketPrice, image, status)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, movie.getMovieName());
            stmt.setString(2, movie.getDescription());
            stmt.setString(3, movie.getReleaseDate());
            stmt.setString(4, movie.getDirectorName());
            stmt.setString(5, movie.getMusicBy());
            stmt.setString(6, movie.getProduceBy());
            stmt.setDouble(7, movie.getTicketPrice());
            stmt.setString(8, movie.getImage());
            stmt.setString(9, movie.getStatus());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    movie.setMovieId(generatedKeys.getInt(1)); // Set the generated movie ID
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT * FROM Movies";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                movies.add(mapMovie(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movies;
    }


    // Get a movie by ID
    public Movie getMovieById(int movieId) {
        String query = "SELECT * FROM Movies WHERE movieId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapMovie(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean deleteMovie(int movieId) {
        String deleteBookingsQuery = "DELETE FROM Bookings WHERE movieId = ?";
        String deleteShowTimesQuery = "DELETE FROM ShowTimes WHERE showDateId IN (SELECT showDateId FROM ShowDate WHERE movieId = ?)";
        String deleteShowDatesQuery = "DELETE FROM ShowDate WHERE movieId = ?";
        String deleteMovieQuery = "DELETE FROM Movies WHERE movieId = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            try (PreparedStatement deleteBookingsStmt = conn.prepareStatement(deleteBookingsQuery);
                 PreparedStatement deleteShowTimesStmt = conn.prepareStatement(deleteShowTimesQuery);
                 PreparedStatement deleteShowDatesStmt = conn.prepareStatement(deleteShowDatesQuery);
                 PreparedStatement deleteMovieStmt = conn.prepareStatement(deleteMovieQuery)) {

                // Step 1: Delete associated bookings
                deleteBookingsStmt.setInt(1, movieId);
                deleteBookingsStmt.executeUpdate();

                // Step 2: Delete associated show times
                deleteShowTimesStmt.setInt(1, movieId);
                deleteShowTimesStmt.executeUpdate();

                // Step 3: Delete associated show dates
                deleteShowDatesStmt.setInt(1, movieId);
                deleteShowDatesStmt.executeUpdate();

                // Step 4: Delete the movie
                deleteMovieStmt.setInt(1, movieId);
                int rowsAffected = deleteMovieStmt.executeUpdate();

                conn.commit(); // Commit transaction if all steps succeed
                return rowsAffected > 0;

            } catch (SQLException e) {
                conn.rollback(); // Rollback transaction in case of failure
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    // Add a new show date
    public boolean addShowDate(ShowDate showDate) {
        String query = "INSERT INTO ShowDate (movieId, showDate) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, showDate.getMovieId());
            stmt.setString(2, showDate.getShowDate());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    showDate.setShowDateId(generatedKeys.getInt(1)); // Set the generated show date ID
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get show dates for a movie
    public List<ShowDate> getShowDatesByMovieId(int movieId) {
        List<ShowDate> showDates = new ArrayList<>();
        String query = "SELECT * FROM ShowDate WHERE movieId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movieId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ShowDate date = mapShowDate(rs);
                    date.setShowTimes(getShowTimesByShowDateId(date.getShowDateId())); // Load show times for the date
                    showDates.add(date);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showDates;
    }

    // Add a new show time
    public boolean addShowTime(ShowTime showTime) {
        String query = "INSERT INTO ShowTimes (movieId,showDateId, showTime) VALUES (?, ?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, showTime.getMovieId());
            stmt.setInt(2, showTime.getShowDateId());
            stmt.setTime(3, Time.valueOf(showTime.getShowTime()));


            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    showTime.setShowTimeId(generatedKeys.getInt(1)); // Set the generated show time ID
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get show times for a show date
    public List<ShowTime> getShowTimesByShowDateId(int showDateId) {
        List<ShowTime> showTimes = new ArrayList<>();
        String query = "SELECT * FROM ShowTimes WHERE showDateId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, showDateId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    showTimes.add(mapShowTime(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return showTimes;
    }

    public ShowTime getShowTimeById(int showTimeId) {
        String query = "SELECT * FROM ShowTimes WHERE showTimeId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, showTimeId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapShowTime(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ShowDate getShowDateById(int showDateId) {
        ShowDate showDate = null;
        String sql = "SELECT showDateId, movieId, showDate FROM ShowDate WHERE showDateId = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, showDateId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    showDate = new ShowDate();
                    showDate.setShowDateId(resultSet.getInt("showDateId"));
                    showDate.setMovieId(resultSet.getInt("movieId")); // Assuming ShowDate class has this field

                    Date dbDate = resultSet.getDate("showDate");
                    if (dbDate != null) {
                        LocalDate localDate = dbDate.toLocalDate();
                        showDate.setShowDate(localDate.toString()); // Assuming setShowDate takes a LocalDate
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("System Error: " + e.getMessage());
            throw new RuntimeException("Error retrieving show date", e);
        }
        return showDate;
    }


    // Map ResultSet to Movie
    private Movie mapMovie(ResultSet rs) throws Exception {
        Movie movie = new Movie();
        movie.setMovieId(rs.getInt("movieId"));
        movie.setMovieName(rs.getString("movieName"));
        movie.setDescription(rs.getString("description"));
        movie.setReleaseDate(rs.getString("releaseDate"));
        movie.setDirectorName(rs.getString("directorName"));
        movie.setMusicBy(rs.getString("musicBy"));
        movie.setProduceBy(rs.getString("produceBy"));
        movie.setTicketPrice(rs.getDouble("ticketPrice"));
        movie.setImage(rs.getString("image"));
        movie.setStatus(rs.getString("status"));
        return movie;
    }


    // Map ResultSet to ShowDate
    private ShowDate mapShowDate(ResultSet rs) throws SQLException {
        return new ShowDate(
                rs.getInt("showDateId"),
                rs.getInt("movieId"),
                rs.getString("showDate")
        );
    }

    // Map ResultSet to ShowTime
    private ShowTime mapShowTime(ResultSet rs) throws SQLException {
        return new ShowTime(
                rs.getInt("showTimeId"),
                rs.getInt("showDateId"),
                rs.getInt("showDateId"),
                rs.getString("showTime")
        );
    }

}
