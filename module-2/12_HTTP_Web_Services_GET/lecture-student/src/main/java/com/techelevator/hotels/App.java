package com.techelevator.hotels;

import com.techelevator.services.ConsoleService;
import com.techelevator.services.HotelService;

import java.util.Scanner;

public class App {
    private static final String API_BASE_URL = "https://te-pgh-api.azurewebsites.net/api/";
    private static final String API_KEY = "999";

    public static void main(String[] args) {
        run();
    }

    private static void run() {
        HotelService hotelService = new HotelService(API_BASE_URL, API_KEY);
        ConsoleService consoleService = new ConsoleService();

        Scanner scanner = new Scanner(System.in);
        int menuSelection = 999;

        printGreeting();

        while(menuSelection != 0) {
            try {
                menuSelection = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException exception) {
                System.out.println("Error parsing the input for menu selection.");
            }
            System.out.println("");
            if (menuSelection == 1) {
                System.out.println("Not implemented");
            } else if (menuSelection == 2) {
                System.out.println("Not implemented");
            } else if (menuSelection == 3) {
                System.out.println("Not implemented");
            } else if (menuSelection == 4) {
                System.out.println("Not implemented");
            } else if (menuSelection == 5) {
                System.out.println("Not implemented");
            } else if (menuSelection == 6) {
                System.out.println("Not implemented - Create a custom Web API query here");
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            menuSelection = 999;
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
            printGreeting();
        }
        scanner.close();
        System.exit(0);
    }

    private static void printGreeting() {
        System.out.println("");
        System.out.println("Welcome to Tech Elevator Hotels. Please make a selection: ");
        System.out.println("1: List Hotels");
        System.out.println("2: List Reviews");
        System.out.println("3: Show Details for Hotel ID 1");
        System.out.println("4: List Reviews for Hotel ID 1");
        System.out.println("5: List Hotels with star rating 3");
        System.out.println("6: Custom Query");
        System.out.println("0: Exit");
        System.out.println("");
        System.out.print("Please choose an option: ");
    }

}
