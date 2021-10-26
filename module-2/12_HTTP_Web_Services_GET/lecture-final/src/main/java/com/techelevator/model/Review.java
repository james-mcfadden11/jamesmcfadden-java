package com.techelevator.model;

public class Review {
    private int hotelID;
    private String title;
    private String reviewText;
    private String author;
    private int stars;

    public Review() {

    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "\n--------------------------------------------" +
                "\n Review Details" +
                "\n--------------------------------------------" +
                "\n Hotel ID: " + hotelID +
                "\n Title: " + title +
                "\n Review: " + reviewText +
                "\n Author: " + author +
                "\n Stars: " + stars;
    }
}
