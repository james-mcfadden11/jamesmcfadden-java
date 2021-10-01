package com.techelevator;

public class GameFlow {
    private final Player playerOne;
    private final Player playerTwo;
    private final Board board;
    private final UI userInterface;

    private Player currentPlayer;

    public GameFlow(Player playerOne, Player playerTwo, Board board, UI userInterface) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.board = board;
        this.userInterface = userInterface;
    }

    public void play() {
        // game logic goes here
    }

    private void switchPlayer() {
        currentPlayer = currentPlayer == playerOne ? playerTwo : playerOne;
    }

    private boolean isADraw() {
        return false;
    }

    private boolean didWin(Player player) {
        Piece p = player.getPiece();

        return didWinHorizontally(p) || didWinVertically(p) || didWinDiagonally(p);
    }

    private boolean didWinHorizontally(Piece p) {
        int consecutive = 0;

        for(int row = board.totalRows() - 1; row >= 0; row--) {
            consecutive = 0;
            for(int column = 0; column < board.totalColumns(); column++) {

                Piece currentSlotPiece = board.get(row, column);
                if(p.getColor().equals(currentSlotPiece.getColor())) {
                    consecutive++;

                    if(consecutive == 4) {
                        return true;
                    }

                } else {
                    consecutive = 0;
                }
            }
        }

        return false;
    }

    private boolean didWinVertically(Piece p) {
        return false;
    }

    private boolean didWinDiagonally(Piece p) {
        return false;
    }
}
