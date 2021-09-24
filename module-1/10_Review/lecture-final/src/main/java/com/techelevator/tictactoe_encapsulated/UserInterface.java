package com.techelevator.tictactoe_encapsulated;

import java.util.Scanner;

public class UserInterface {
    private final Scanner userInput;

    public UserInterface() {
        userInput = new Scanner(System.in);
    }

    public int getSlotNumber(TicTacToeCharacter character) {
        System.out.print("Enter a slot number for " + character + ": ");
        return Integer.parseInt(userInput.nextLine());
    }

    public void displayBoard(Board board) {
        System.out.println(board);
    }

    public void displayDraw() {
        System.out.println("It's a draw!");
    }

    public void displayWin(TicTacToeCharacter character) {
        System.out.println("The winner is " + character);
    }

    public void displayInvalidSlot() {
        System.out.println("Invalid slot number.");
    }
}
