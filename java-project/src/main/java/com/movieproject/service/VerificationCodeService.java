package com.movieproject.service;


import com.movieproject.model.VerificationCode;
import com.movieproject.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class VerificationCodeService {

    // Add a new verification code
    public boolean addVerificationCode(int userId, String code, LocalDateTime expiresAt) {
        String query = "INSERT INTO VerificationCode (userId, code, createdAt, expiresAt, isUsed) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            statement.setString(2, code);
            statement.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            statement.setTimestamp(4, Timestamp.valueOf(expiresAt));
            statement.setBoolean(5, false); // isUsed = false

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Mark a verification code as used
    public boolean markCodeAsUsed(int userId, String code) {
        String query = "UPDATE VerificationCode SET isUsed = ? WHERE userId = ? AND code = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBoolean(1, true); // isUsed = true
            statement.setInt(2, userId);
            statement.setString(3, code);

            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get a verification code for a specific user and code value
    public Optional<VerificationCode> getVerificationCode(int userId, String code) {
        String query = "SELECT * FROM VerificationCode WHERE userId = ? AND code = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, userId);
            statement.setString(2, code);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                VerificationCode verificationCode = new VerificationCode(
                        resultSet.getInt("id"),
                        resultSet.getInt("userId"),
                        resultSet.getString("code"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime(),
                        resultSet.getTimestamp("expiresAt").toLocalDateTime(),
                        resultSet.getBoolean("isUsed")
                );
                return Optional.of(verificationCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Verify if a code is valid (not expired and not used)
    public boolean verifyCode(int userId, String code) {
        Optional<VerificationCode> optionalCode = getVerificationCode(userId, code);
        if (optionalCode.isPresent()) {
            VerificationCode verificationCode = optionalCode.get();
            // Check if the code is not expired and not used
            return verificationCode.isValid();
        }
        return false;
    }

}

