package com.techelevator;

public class Lecture {
    public static void main(String[] args) {

        UI commandLineInterface = null; // TBD

        Board board = new Board();

        Piece playerOnePiece = new Piece("Red");
        Player playerOne = new UIPlayer(playerOnePiece, commandLineInterface);

        Piece playerTwoPiece = new Piece("Yellow");
        Player playerTwo = new AIPlayer(playerTwoPiece);

        GameFlow game = new GameFlow(playerOne, playerTwo, board, commandLineInterface);

        game.play();

    }

    /*

        Piece
          -> Player
            -> User Interface
              -> Game Flow
          -> Board
              -> Game Flow

        AIPlayer extends Player
        UIPlayer extends Player

        class - Player
         - Piece
         - AI Logic / User Input

        class / method - AI Logic
         - method - Get Next Move

        class - Piece
          - Colors (Discs)

        class - Board Thing
            attribute - Piece[6][7]
            method - Drop Disc
            method - Get Piece At Position

        class - User Interface
            method - Get Next Move or Forfeit
            method - Display Board Configuration
            method - Display Winner / Draw
            method - Play Again?
            method - Display Invalid Move
            method - Pieces Remaining

        class - Game Flow
            attribute - Current Player
            method - Did Someone Win?
            method - Who won?
            method - Draw (Full board)
            method - Clear to board
            method - Game Over


     */



}
