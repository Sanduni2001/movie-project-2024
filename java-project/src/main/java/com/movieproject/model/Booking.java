package com.movieproject.model;

public class Booking {
    private int bookingId;      // Unique ID for the booking
    private int userId;         // ID of the user who made the booking
    private int movieId;        // ID of the movie being booked
    private String showDate;    // Date of the show (formatted as String, e.g., "2024-12-19")
    private String showTime;    // Time of the show (formatted as String, e.g., "18:00")
    private int numberOfSeats;  // Number of seats booked
    private double totalPrice;  // Total price for the booking
    private String status; // e.g., "Pending", "Paid", "Canceled"

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Default constructor (necessary for frameworks like Hibernate, JSON parsing, etc.)
    public Booking() {
    }

    // Constructor with all parameters (useful for creating a Booking object)
    public Booking(int bookingId, int userId, int movieId, String showDate, String showTime,
                   int numberOfSeats, double totalPrice) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.movieId = movieId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
    }

    // Constructor without bookingId (useful for new bookings before ID is generated)
    public Booking(int userId, int movieId, String showDate, String showTime, int numberOfSeats, double totalPrice) {
        this.userId = userId;
        this.movieId = movieId;
        this.showDate = showDate;
        this.showTime = showTime;
        this.numberOfSeats = numberOfSeats;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters for all fields

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Optional toString method for debugging and logging
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", showDate='" + showDate + '\'' +
                ", showTime='" + showTime + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
