package com.techelevator.locations;

import com.techelevator.model.Location;
import com.techelevator.services.ConsoleService;
import com.techelevator.services.LocationService;
import org.springframework.web.client.RestTemplate;

public class App {
  private static final String API_URL = "https://te-pgh-api.azurewebsites.net/api/locations";
  private static final String API_KEY = "03037";
    
  public static void main(String[] args) {
    int menuSelection = 999;

    ConsoleService consoleService = new ConsoleService();
    LocationService locationService = new LocationService(API_URL, API_KEY);

    while (menuSelection != 0) {
      menuSelection = consoleService.printMainMenu();
      if (menuSelection == 1) {
        Location[] locations = locationService.getAll();
        consoleService.printLocations(locations);

      } else if (menuSelection == 2) {
        Location[] locations = locationService.getAll();
        int locationId = consoleService.promptForLocation(locations, "View");
        consoleService.printLocation(locationService.getOne(locationId));

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
