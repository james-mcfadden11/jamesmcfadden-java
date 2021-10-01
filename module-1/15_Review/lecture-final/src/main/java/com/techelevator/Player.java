package com.techelevator;

public abstract class Player {
    private final Piece piece;

    public Player(Piece piece) {
        this.piece = piece;
    }

    public abstract int getNextMove();

    public Piece getPiece() {
        return piece;
    }
}
