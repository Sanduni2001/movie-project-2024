package com.movieproject.service;

import com.movieproject.model.User;
import com.movieproject.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    // Show users by role
    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users WHERE role = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Show all users
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Add a new user (Admin or Client)
    public boolean addUser(User user) {
        String query = "INSERT INTO Users (username, email, password, mobile, role, isActive, emailVerified) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getMobile());
            stmt.setString(5, user.getRole());
            stmt.setBoolean(6, user.getIsActive());
            stmt.setBoolean(7, user.isEmailVerified());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add an Admin user
    public boolean addAdmin(User user) {
        user.setRole("ADMIN");
        user.setActive(true); // Default active
        user.setEmailVerified(true); // Default email verified

        return addUser(user); // Reuse addUser logic
    }

    // Update user details
    public boolean updateUser(User user) {
        String query = "UPDATE Users SET username = ?, email = ?, mobile = ?, role = ?, isActive = ?, emailVerified = ? WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getMobile());
            stmt.setString(4, user.getRole());
            stmt.setBoolean(5, user.getIsActive());
            stmt.setBoolean(6, user.isEmailVerified());
            stmt.setInt(7, user.getId());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete user by ID
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM Users WHERE userID = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userId);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int adminId) {
        String query = "SELECT * FROM Users WHERE userID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, adminId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Return null if no user found
    }


    // Map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("userID"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getInt("mobile"),
                rs.getString("role"),
                rs.getBoolean("isActive"),
                rs.getBoolean("emailVerified")
        );
    }


}
