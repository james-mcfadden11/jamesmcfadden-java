package com.techelevator.tictactoe_encapsulated;

public class Board {

    private final TicTacToeCharacter[] board = new TicTacToeCharacter[9];

    public Board() {
        for (int a = 0; a < 9; a++) {
            board[a] = TicTacToeCharacter.EMPTY;
        }
    }

    public TicTacToeCharacter get(int slotNumber) {
        return board[slotNumber - 1];
    }

    private void set(int slotNumber, TicTacToeCharacter character) {
        board[slotNumber - 1] = character;
    }

    public boolean makeMove(TicTacToeCharacter character, int slotNumber) {
        if(!TicTacToe.VALID_SLOTS.contains(slotNumber)) {
            return false;
        }

        if(get(slotNumber).equals(TicTacToeCharacter.EMPTY)) {
            set(slotNumber, character);
            return true;
        }

        return false;
    }

    public String toString() {
        return  "|---|---|---|\n" +
                "| " + stringAt(1)  + " | " + stringAt(2)  + " | " + stringAt(3)  + " |\n" +
                "| " + stringAt(4)  + " | " + stringAt(5)  + " | " + stringAt(6)  + " |\n" +
                "| " + stringAt(7)  + " | " + stringAt(8)  + " | " + stringAt(9)  + " |\n" +
                "|---|---|---|";
    }

    private String stringAt(int slotNumber) {
        TicTacToeCharacter character = get(slotNumber);

        return character.equals(TicTacToeCharacter.EMPTY) ? String.valueOf(slotNumber) : character.toString();
    }
}
