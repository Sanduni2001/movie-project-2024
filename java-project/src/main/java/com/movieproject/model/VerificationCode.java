package com.movieproject.model;

import java.time.LocalDateTime;

public class VerificationCode {
    private int id; // Primary key
    private int userId; // User ID associated with the verification code
    private String code; // The verification code
    private LocalDateTime createdAt; // Timestamp when the code was created
    private LocalDateTime expiresAt; // Timestamp when the code expires
    private boolean isUsed; // Whether the code has been used or not

    // Default constructor
    public VerificationCode() {
    }

    // Parameterized constructor
    public VerificationCode(int id, int userId, String code, LocalDateTime createdAt, LocalDateTime expiresAt, boolean isUsed) {
        this.id = id;
        this.userId = userId;
        this.code = code;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.isUsed = isUsed;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    // Method to check if the code is valid (not expired and not used)
    public boolean isValid() {
        return !isUsed && expiresAt.isAfter(LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "VerificationCode{" +
                "id=" + id +
                ", userId=" + userId +
                ", code='" + code + '\'' +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                ", isUsed=" + isUsed +
                '}';
    }
}
