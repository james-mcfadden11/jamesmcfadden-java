package com.techelevator;

public class Card {
    // fields
    private int rank;
    private String suit;
    private boolean isFaceDown = true;

    // constructors
    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Card(int rank, String suit, boolean isFaceDown) {
        this.rank = rank;
        this.suit = suit;
        this.isFaceDown = isFaceDown;
    }


    // methods


    // getters and setters
    public int getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    public boolean isFaceDown() {
        return isFaceDown;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }

    public void setFaceDown(boolean faceDown) {
        isFaceDown = faceDown;
    }
}
