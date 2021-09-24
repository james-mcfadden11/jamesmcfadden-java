package com.techelevator;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String genre;
    private Person author;
    private double price;
    private boolean isNew = true;
    private boolean signed;
    private String location;

    private List<String> reviews;

    public Book(String title, Person author) {
        this.title = title;
        this.author = author;
        this.reviews = new ArrayList<>();
    }

    public void leaveReview(String review) {
        this.reviews.add(review);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }
}
