package com.techelevator.hotels;

import com.techelevator.model.City;
import com.techelevator.model.Hotel;
import com.techelevator.model.Review;
import com.techelevator.services.ConsoleService;
import com.techelevator.services.HotelService;

import java.util.Scanner;

public class App {
    private static final String API_BASE_URL = "https://te-pgh-api.azurewebsites.net/api/";
    private static final String API_KEY = "03037";

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
                Hotel[] hotels = hotelService.listHotels();
                consoleService.printHotels(hotels);
            } else if (menuSelection == 2) {
                Review[] reviews = hotelService.listReviews();
                consoleService.printReviews(reviews);
            } else if (menuSelection == 3) {
                // hard-coding 1 as the hotel ID
                consoleService.printHotel(hotelService.getHotelById(1));
            } else if (menuSelection == 4) {
                Review[] reviewsForHotel1 = hotelService.getReviewsByHotelId(1);
                consoleService.printReviews(reviewsForHotel1);
            } else if (menuSelection == 5) {
                Hotel[] hotels = hotelService.getHotelsByStarRating(3);
                consoleService.printHotels(hotels);
            } else if (menuSelection == 6) {
                City pittsburgh = hotelService.getWithCustomQuery();
                System.out.println(pittsburgh);
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
