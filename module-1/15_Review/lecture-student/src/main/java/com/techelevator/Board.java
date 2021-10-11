package com.techelevator;

public class Board {
    /*

       0 1 2 3 4 5 6
    0  - - - - - - -
    1  - - - - - - -
    2  - - - - - - -
    3  - - - - - - -
    4  - - - - - - -
    5  - - - - - - -

    */

    private final Piece[][] board;
    private static final int DEFAULT_ROW_COUNT = 6;
    private static final int DEFAULT_COLUMN_COUNT = 7;


    public Board() {
        this.board = new Piece[6][7];
    }

    public boolean dropPiece(int column, Piece piece) {
        // invalid column
        if (column < 0 || column > 6) {
            return false;
        }

        // check if column is full
        if (board[0][column] != null) {
            return false;
        }

        for (int row = 5; row >= 0; row--) {
            if (board[row][column] == null) {
                board[row][column] = piece;
                return true;
            }
        }

        return false;
    }

    public Piece get(int row, int column) {
        return board[row][column];
    }

}
