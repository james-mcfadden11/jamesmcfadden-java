package com.techelevator.exceptions;

public class NegativeInputException extends RuntimeException {
    private int whatTheInputWas;

    public NegativeInputException(String message, int whatTheInputWas) {
        super(message);
        this.whatTheInputWas = whatTheInputWas;
    }

    public int getWhatTheInputWas() {
        return whatTheInputWas;
    }

}
