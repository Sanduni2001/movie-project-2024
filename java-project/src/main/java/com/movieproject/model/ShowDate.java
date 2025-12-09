package com.movieproject.model;


import java.util.ArrayList;
import java.util.List;

public class ShowDate {
    private int showDateId;
    private int movieId;
    private String showDate;
    private List<ShowTime> showTimes;

    public ShowDate() {}

    public ShowDate(int showDateId, int movieId, String showDate) {
        this.showDateId = showDateId;
        this.movieId = movieId;
        this.showDate = showDate;
        this.showTimes = new ArrayList<>();
    }

    // Getters and setters
    public List<ShowTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    public int getShowDateId() {
        return showDateId;
    }

    public void setShowDateId(int showDateId) {
        this.showDateId = showDateId;
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
}

