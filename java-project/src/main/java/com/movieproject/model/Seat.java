package com.movieproject.model;


public class Seat {
    private int seatId;
    private int showTimeId;
    private int seatNumber;
    private boolean isBooked;

    public Seat() {}

    public Seat(int seatId, int showTimeId, int seatNumber, boolean isBooked) {
        this.seatId = seatId;
        this.showTimeId = showTimeId;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    // Getters and Setters
    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public int getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(int showTimeId) {
        this.showTimeId = showTimeId;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
