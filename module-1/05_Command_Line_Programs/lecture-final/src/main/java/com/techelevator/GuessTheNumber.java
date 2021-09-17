package com.techelevator;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the lower bound: ");
        String lowerBoundText = scanner.nextLine();
        int lowerBound = Integer.parseInt(lowerBoundText);

        int upperBound;
        while (true) {
            System.out.print("Enter the upper bound: ");
            String upperBoundText = scanner.nextLine();
            upperBound = Integer.parseInt(upperBoundText);

            if (upperBound <= lowerBound) {
                System.out.println("Upper bound must be larger than the lower bound.");
            } else {
                break;
            }
        }


        final int LOWER_BOUND = lowerBound;
        final int UPPER_BOUND = upperBound;

        int numberToGuess = getRandom(LOWER_BOUND, UPPER_BOUND);

        int guess;

        do {
            System.out.print("Enter your guess between " + LOWER_BOUND + " - " + UPPER_BOUND + ": ");
            String line = scanner.nextLine();
            guess = Integer.parseInt(line);

            if (guess < LOWER_BOUND || guess > UPPER_BOUND) {
                System.out.println("Guess invalid!");
                continue;
            }

            if (guess < numberToGuess) {
                System.out.println("Guess higher");
            } else if (guess > numberToGuess) {
                System.out.println("Guess lower");
            } else {
                System.out.println("You got it!");
            }
        } while(guess != numberToGuess);
    }

    public static int getRandom(int lowerBound, int upperBound) {
        Random r = new Random();
        return r.nextInt(upperBound + 1 - lowerBound) + lowerBound;
    }

    public void checkEquality() {


        String x = "X";
        String y = "X";

       boolean z = x.equals(y);

    }
}
