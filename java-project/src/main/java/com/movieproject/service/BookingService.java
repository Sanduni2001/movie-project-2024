package com.movieproject.service;

import com.movieproject.model.Booking;
import com.movieproject.utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingService {

    // Create a new booking
    public boolean createBooking(Booking booking) {
        String query = "INSERT INTO Bookings (userId, movieId, showDate, showTime, numberOfSeats, totalPrice) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, booking.getUserId());
            stmt.setInt(2, booking.getMovieId());
            stmt.setString(3, booking.getShowDate());
            stmt.setString(4, booking.getShowTime());
            stmt.setInt(5, booking.getNumberOfSeats());
            stmt.setDouble(6, booking.getTotalPrice());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // Get all bookings by user ID
    public List<Booking> getAllBookingsByUserId(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Bookings WHERE userId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                bookings.add(mapBooking(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }

    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM Bookings";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                bookings.add(mapBooking(rs)); // Ensure mapBooking method maps all necessary fields.
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace this with a proper logging framework in production.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }


    // Get booking by booking ID
    public Booking getBookingById(int bookingId) {
        Booking booking = null;
        String query = "SELECT * FROM Bookings WHERE bookingId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                booking = mapBooking(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return booking;
    }

    // Delete a booking
    public boolean deleteBooking(int bookingId) {
        String query = "DELETE FROM Bookings WHERE bookingId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

//    public boolean updateBookingStatus(int bookingId, String status) {
//        String query = "UPDATE Bookings SET status = ? WHERE order_id = ?";
//        try (Connection conn = DBConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, status);
//            stmt.setInt(2, bookingId);
//
//            return stmt.executeUpdate() > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


    // Utility method to map a ResultSet to a Booking object
    private Booking mapBooking(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("bookingId"));
        booking.setUserId(rs.getInt("userId"));
        booking.setMovieId(rs.getInt("movieId"));
        booking.setShowDate(rs.getString("showDate"));
        booking.setShowTime(rs.getString("showTime"));
        booking.setNumberOfSeats(rs.getInt("numberOfSeats"));
        booking.setTotalPrice(rs.getDouble("totalPrice"));
        return booking;
    }
}
