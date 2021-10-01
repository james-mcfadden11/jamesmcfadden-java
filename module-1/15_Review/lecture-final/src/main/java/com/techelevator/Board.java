package com.techelevator;

public class Board {
    private static final int DEFAULT_ROW_COUNT = 6;
    private static final int DEFAULT_COLUMN_COUNT = 7;
    private final int rowCount;
    private final int columnCount;
    /*
       - - - - - - -
       - - - - - - -
       - - - - - - -
       - - - - - - -
       - - x - - - -
       - - x - - - -
     */
    private final Piece[][] board;

    public Board() {
        rowCount = DEFAULT_ROW_COUNT;
        columnCount = DEFAULT_COLUMN_COUNT;
        board = new Piece[rowCount][columnCount];
    }

    public boolean dropPiece(int column, Piece piece) {

        // invalid column
        if(column < 0 || column > 6) {
            return false;
        }

        // column is full
        if(board[0][column] != null) {
            return false;
        }

        for(int row = rowCount - 1; row >= 0; row--) {
            if(board[row][column] == null) {
                board[row][column] = piece;
                return true;
            }
        }

        return false;
    }

    public Piece get(int row, int column) {
        return board[row][column];
    }

    public int totalRows() {
        return rowCount;
    }

    public int totalColumns() {
        return columnCount;
    }

}
