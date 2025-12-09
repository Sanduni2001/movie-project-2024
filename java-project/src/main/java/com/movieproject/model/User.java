package com.movieproject.model;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private int mobile;
    private String role;
    private boolean isActive;
    private boolean emailVerified;

    // Default constructor
    public User() {
    }

    // Parameterized constructor
    public User(int id, String username, String email, String password, int mobile, String role, boolean isActive, boolean emailVerified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.role = role;
        this.isActive = isActive;
        this.emailVerified = emailVerified;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getIsActive() {
        return isActive; // Updated to "getIsActive" for JSP compatibility
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    // Helper methods
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(this.role);
    }

    public boolean isClient() {
        return "client".equalsIgnoreCase(this.role);
    }
}
