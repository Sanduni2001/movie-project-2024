package com.movieproject.constant;

/**
 * Application-wide constants
 */
public final class AppConstants {
    
    private AppConstants() {
        // Utility class - prevent instantiation
    }
    
    // Session attributes
    public static final String SESSION_USER = "user";
    public static final String SESSION_USER_ID = "userId";
    
    // Request attributes
    public static final String REQUEST_MOVIES = "movies";
    public static final String REQUEST_ERROR = "error";
    public static final String REQUEST_SUCCESS = "success";
    
    // URL patterns
    public static final String URL_HOME = "/home";
    public static final String URL_LOGIN = "/auth/login";
    public static final String URL_REGISTER = "/auth/register";
    
    // File upload
    public static final String UPLOAD_DIR = "DBImages";
    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024; // 5MB
    
    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 10;
}
