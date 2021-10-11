package com.techelevator;

public class NegativeInputException extends RuntimeException {
    // if you dont use a constructor, it will use default constructor
    // of super class RuntimeException

    private int whatTheInputWas;

    public NegativeInputException(String message, int whatTheInputWas) {
        super(message);
        this.whatTheInputWas = whatTheInputWas;
    }

    public int getWhatTheInputWas() {
        return whatTheInputWas;
    }

}
