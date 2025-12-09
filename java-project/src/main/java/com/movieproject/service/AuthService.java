package com.movieproject.service;

import com.movieproject.model.User;
import com.movieproject.model.UserRoles;
import com.movieproject.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthService {

    private final VerificationCodeService verificationCodeService = new VerificationCodeService();
    private final EmailService emailService = new EmailService();
    private static final Logger LOGGER = Logger.getLogger(AuthService.class.getName());


    // Registers a new user
    public boolean registerUser(String username, String email,int mobile, String password) {
        if (isEmailExists(email)) {
            throw new IllegalArgumentException("Email already exists. Please use a different email.");
        }

        String query = "INSERT INTO users (username, email, mobile, password, role, isActive, emailVerified)" +
                "VALUES (?, ?, ?, ?, ?, false, false);";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setInt(3, mobile);
            statement.setString(4, password);
            statement.setString(5, UserRoles.CLIENT.toString());


            int rows = statement.executeUpdate();
            if (rows > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    return sendVerificationCode(userId, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // Checks if an email already exists in the database
    private boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // If count > 0, email exists
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    // Logs in a user
    public User loginUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                boolean isActive = resultSet.getBoolean("isActive");
                boolean emailVerified = resultSet.getBoolean("emailVerified");

                if (!isActive) {
                    throw new IllegalStateException("Account is inactive. Please contact support.");
                }

                if (!emailVerified) {
                    throw new IllegalStateException("Email not verified. Please verify your email.");
                }

                return new User(
                        resultSet.getInt("userID"),
                        resultSet.getString("username"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getInt("mobile"),
                        resultSet.getString("role"),
                        isActive,
                        emailVerified
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Sends a verification email to the user
    public boolean sendVerificationCode(int userId, String email) {
        String code = generateCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(15);

        if (verificationCodeService.addVerificationCode(userId, code, expiresAt)) {
            try {
                emailService.sendVerificationEmail(email, code);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Verifies the code entered by the user
    public boolean verifyCode(int userId, String code) {
        if (verificationCodeService.verifyCode(userId, code)) {
            verificationCodeService.markCodeAsUsed(userId, code);
            return activateUser(userId);
        }
        return false;
    }

    // Activates the user account after successful verification
    private boolean activateUser(int userId) {
        String query = "UPDATE users SET emailVerified = true, isActive = true WHERE userID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Handles forgot password by sending a reset code using email
    public boolean sendForgotPasswordCode(String email) {
        int userId = getUserIdByEmail(email);
        if (userId == -1) {
            return false; // User with the email doesn't exist
        }

        String code = generateCode();
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(15);

        if (verificationCodeService.addVerificationCode(userId, code, expiresAt)) {
            try {
                emailService.sendForgotPasswordEmail(email, code);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Gets the user ID using the email
    public int getUserIdByEmail(String email) {
        String query = "SELECT userID FROM Users WHERE email = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("userID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // User not found
    }

    // Resets the user's password
    public boolean resetPassword(String email, String code, String newPassword) {
        int userId = getUserIdByEmail(email);
        if (userId == -1) {
            LOGGER.log(Level.WARNING, "No user found with email: {0}", email);
            return false;
        }

        if (verificationCodeService.verifyCode(userId, code)) {
            verificationCodeService.markCodeAsUsed(userId, code);
            return updatePassword(userId, newPassword);
        } else {
            LOGGER.log(Level.INFO, "Verification code mismatch for user ID: {0}", userId);
            return false;
        }
    }


    // Updates the user's password in the database
    private boolean updatePassword(int userId, String newPassword) {
        String query = "UPDATE Users SET password = ? WHERE userID = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newPassword);
            statement.setInt(2, userId);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to update password for user ID: " + userId, e);
            return false;
        }
    }

    // Utility method to generate a 6-digit verification code
    private String generateCode() {
        return String.valueOf(100000 + (int) (Math.random() * 900000)); // Generate random 6-digit code
    }
}
