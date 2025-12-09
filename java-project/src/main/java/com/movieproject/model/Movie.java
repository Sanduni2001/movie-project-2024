package com.movieproject.model;

public class Movie {
    private int movieId;
    private String movieName;
    private String description;
    private String releaseDate;
    private String directorName;
    private String musicBy;
    private String produceBy;
    private double ticketPrice;
    private String image;
    private String status; // New field

    // Default constructor
    public Movie() {}

    // Parameterized constructor
    public Movie(int movieId, String movieName, String description, String releaseDate, String directorName,
                 String musicBy, String produceBy, double ticketPrice, String image, String status) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.description = description;
        this.releaseDate = releaseDate;
        this.directorName = directorName;
        this.musicBy = musicBy;
        this.produceBy = produceBy;
        this.ticketPrice = ticketPrice;
        this.image = image;
        this.status = status;
    }

    // Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getMusicBy() {
        return musicBy;
    }

    public void setMusicBy(String musicBy) {
        this.musicBy = musicBy;
    }

    public String getProduceBy() {
        return produceBy;
    }

    public void setProduceBy(String produceBy) {
        this.produceBy = produceBy;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", directorName='" + directorName + '\'' +
                ", musicBy='" + musicBy + '\'' +
                ", produceBy='" + produceBy + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
