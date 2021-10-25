package com.techelevator.locations;

import com.techelevator.services.ConsoleService;
import com.techelevator.services.LocationService;

public class App {
  private static final String API_URL = "https://te-pgh-api.azurewebsites.net/api/locations";
  private static final String API_KEY = "0000";
    
  public static void main(String[] args) {
    int menuSelection = 999;

    ConsoleService consoleService = new ConsoleService();
    LocationService locationService = new LocationService(API_URL, API_KEY);

    while (menuSelection != 0) {
      menuSelection = consoleService.printMainMenu();
      if (menuSelection == 1) {
        // TODO: list all locations

      } else if (menuSelection == 2) {
        // TODO: get one location

      } else if (menuSelection == 0) {
        // exit
        consoleService.exit();
      } else {
        // defensive programming: anything else is not valid
        System.out.println("Invalid Selection");
      }
    }

  }

}
