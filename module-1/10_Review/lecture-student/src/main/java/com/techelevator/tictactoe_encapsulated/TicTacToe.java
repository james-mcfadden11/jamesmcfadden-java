package com.techelevator.tictactoe_encapsulated;

import java.util.Set;

public class TicTacToe {
    private final Board board;
    private final UserInterface userInterface;
    private TicTacToeCharacter currentCharacter;
    public static final Set<Integer> VALID_SLOTS = Set.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public TicTacToe(Board board, UserInterface userInterface) {
        this.board = board;
        this.userInterface = userInterface;
        this.currentCharacter = TicTacToeCharacter.X;
    }

    public void play() {
        do {
            userInterface.displayBoard(board);
            switchCurrentCharacter();

            int slotNumber;
            do {
                slotNumber = userInterface.getSlotNumber();

                if(!VALID_SLOTS.contains(slotNumber)) {
                    userInterface.displayInvalidSlot();
                }

            } while(!board.makeMove(currentCharacter, slotNumber));


        } while(!isGameOver());


        userInterface.displayBoard(board);

        if(isDraw()) {
            userInterface.displayDraw();
        } else {
            userInterface.displayWin(currentCharacter);
        }

    }

    private void switchCurrentCharacter() {
        if(currentCharacter.equals(TicTacToeCharacter.X)) {
            currentCharacter = TicTacToeCharacter.O;
        } else {
            currentCharacter = TicTacToeCharacter.X;
        }
    }

    private boolean isGameOver() {
        return isWin() || isDraw();
    }

    private boolean isDraw() {
        for(int i = 1; i <= 9; i++) {
            if (board.get(i).equals(TicTacToeCharacter.EMPTY)) {
                return false;
            }
        }
        
        return true;
    }

    private boolean isWin() {
        // Horizontal
        if(board.get(1) != TicTacToeCharacter.EMPTY && board.get(1) == board.get(2) && board.get(2) == board.get(3)) {
            return true;
        }

        if(board.get(4) != TicTacToeCharacter.EMPTY && board.get(4) == board.get(5) && board.get(5) == board.get(6)) {
            return true;
        }

        if(board.get(7) != TicTacToeCharacter.EMPTY  && board.get(7) == board.get(8) && board.get(8) == board.get(9)) {
            return true;
        }

        // Vertical
        if(board.get(1) != TicTacToeCharacter.EMPTY && board.get(1) == board.get(4) && board.get(4) == board.get(7)) {
            return true;
        }

        if(board.get(4) != TicTacToeCharacter.EMPTY && board.get(2) == board.get(5) && board.get(5) == board.get(8)) {
            return true;
        }

        if(board.get(7) != TicTacToeCharacter.EMPTY  && board.get(3) == board.get(6) && board.get(6) == board.get(9)) {
            return true;
        }


        // Diagonal
        if(board.get(1) != TicTacToeCharacter.EMPTY && board.get(1) == board.get(5) && board.get(5) == board.get(9)) {
            return true;
        }

        if(board.get(7) != TicTacToeCharacter.EMPTY && board.get(7) == board.get(5) && board.get(5) == board.get(3)) {
            return true;
        }

        return false;
    }



    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe(new Board(), new UserInterface());
        ticTacToe.play();
    }
}
