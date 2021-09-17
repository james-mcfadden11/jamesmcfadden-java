package com.techelevator;
import java.util.Scanner;

class DiscountCalculator {
    /**
     * The main method is the start and end of our program
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int[] x = { 1 , 2, 3 };

        System.out.println("Welcome to the Discount Calculator");

        // Prompt the user for a discount amount
        // The answer needs to be saved as a double
        System.out.print("Enter the discount amount between 0 and 1 (w/out percentage): ");
        String line = scanner.nextLine();
        double discount = Double.parseDouble(line);
        System.out.println(discount);

        // Prompt the user for a series of prices
        System.out.print("Please provide a series of prices (space separated): ");
        line = scanner.nextLine();
        String[] pricesAsText = line.split(" ");

        for (String priceText : pricesAsText) {
            double price = Double.parseDouble(priceText);
            double discountedPrice = price - (price * discount);
            System.out.println("old price: " + price + " -- new price: " + discountedPrice);
        }

    }

}