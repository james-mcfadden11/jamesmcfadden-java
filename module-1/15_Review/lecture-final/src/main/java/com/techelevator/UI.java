package com.techelevator;

public interface UI {
    int askForNextMove();

    void displayBoard();

    void displayWinner();

    void displayDraw();

    void displayInvalidMove();
}
