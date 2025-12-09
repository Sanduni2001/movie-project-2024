package com.movieproject.model;


import java.util.Date;

public class Review {

    private  int id;
    private  int userId;
    private String description;
    private int stars;
    private Date createDate;

    public Review(int userId, String description, int stars) {
        this.userId = userId;
        this.description = description;
        this.stars = stars;
        this.createDate = new Date();
    }


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
