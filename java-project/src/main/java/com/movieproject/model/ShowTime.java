package com.movieproject.model;

public class ShowTime {
    private int showTimeId;
    private int movieId;
    private int showDateId;
    private String showTime;

    // Constructors, getters, and setters
    public ShowTime() {
    }

    public ShowTime(int showTimeId, int movieId, int showDateId, String showTime) {
        this.showTimeId = showTimeId;
        this.movieId = movieId;
        this.showDateId = showDateId;
        this.showTime = showTime;
    }

    public int getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(int showTimeId) {
        this.showTimeId = showTimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getShowDateId() {
        return showDateId;
    }

    public void setShowDateId(int showDateId) {
        this.showDateId = showDateId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }


}
