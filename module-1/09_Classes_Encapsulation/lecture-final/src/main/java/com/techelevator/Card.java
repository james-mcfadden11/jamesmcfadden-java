package com.techelevator;

public class Card {
    // Fields
    private int rank; // add a way to get the name
    private String suit;
    private boolean isFaceDown;

    // Constructors
    public Card(int rank, String suit) {
        this(rank, suit, true);
    }

    /***
     *
     * @param rank
     * @param suit
     * @param isFaceDown
     */
    public Card(int rank, String suit, boolean isFaceDown) {
        this.rank = rank;
        this.suit = suit;
        this.isFaceDown = isFaceDown;
    }


    // Methods
    public void flip() {
        isFaceDown = !isFaceDown;
    }

    /***
     * Gets the color based on the suit.
     * @return a String representation of the color.
     */
    public String getColor() {
        if(suit.equals("Spades") || suit.equals("Clubs")) {
            return "Black";
        } else {
            return "Red";
        }
    }

    public String getStringRepresentation() {
        if(isFaceDown) {
            return "XX";
        }

        return this.rank + this.suit;
    }


    // Getters and Setters
    public int getRank() {
        return this.rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
}
