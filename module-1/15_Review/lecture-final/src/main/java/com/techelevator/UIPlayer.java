package com.techelevator;

public class UIPlayer extends Player {
    private final UI userInterface;

    public UIPlayer(Piece piece, UI userInterface) {
        super(piece);
        this.userInterface = userInterface;
    }

    @Override
    public int getNextMove() {
        return userInterface.askForNextMove();
    }
}
