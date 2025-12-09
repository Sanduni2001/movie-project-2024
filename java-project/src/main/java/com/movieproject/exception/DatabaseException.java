package com.movieproject.exception;

/**
 * Exception for database-related errors
 */
public class DatabaseException extends AppException {
    
    public DatabaseException(String message) {
        super(message);
    }
    
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}

