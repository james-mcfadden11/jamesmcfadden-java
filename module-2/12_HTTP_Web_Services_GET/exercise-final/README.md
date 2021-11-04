# Web Services GET exercise (Java)

In this exercise, you'll work on a command-line application that displays online auction info. A portion of the command-line application is provided. You'll write the remaining functionality.

You'll add web API calls using RestTemplate to retrieve a list of auctions, details for a single auction, and filter the list of auctions by title and current bid.

## Step One: Identify your API Key
Before you can access the web API, you need a special Key. This Key is called an `API Key`. An `API key` or application programming interface key is a code that gets passed to the web API by the client computer application. In this case, the API key is passed by the command-line application. The `API Key` is used to identify its user, developer or calling program to a website.

In your case, the API Key is the Tech Elevator Inventory Number located on the tag on your laptop. In some cases, that tag is located on the bottom or your laptop.

## Step Two: Explore the API

Before moving on to the next step, explore the web API using Postman. You can access the following endpoints:

- GET: https://te-pgh-api.azurewebsites.net/api/auctions?apikey={your_api_key}
- GET: https://te-pgh-api.azurewebsites.net/api/auctions/{id}?apikey={your_api_key} (use a number between 1 and 7 in place of `{id}`)

## Step Three: Review the starting code

### Data model

There's a class provided in `/src/main/java/com/techelevator/auction/Auction.java` that represents the data model for an auction object. If you've looked at the JSON results from the API, the properties for the class should look familiar.

### Provided code

In `App.java`, you'll find the main method, which is the entry point for the program.

In `ConsoleService.java`, you'll find methods that prompt for user input and print information to the console:

- `printMainMenu()`: Prints a menu that the user will select options from
- `printAuctions()`: Prints a list of auctions
- `printAuction()`: Prints a single auction

- `promptForAuction()`: Lists all auctions and prompts user to choose an auction
- `promptForAuctionTitle()`: Prompts user to search for auctions by title
- `promptForAuctionPrice()`: Prompts user to search for auctions by price

Each of these methods above is called in response to the menu selection within the `while` loop in `App.java`. The `if` statements determine which method to call based upon the menu selection. Notice that each of the four methods below that you'll implement are called there as well.

### Your code

There are four other methods where you'll add code to call the API methods:

- `listAllAuctions()`
- `listDetailsForAuction()`
- `findAuctionsSearchTitle()`
- `findAuctionsSearchPrice()`

In the `run()` method, find the `while` loop. Notice how each menu option is evaluated and these methods are called. The `while` loop returns users back to the menu.

## Step Four: Write the console application

There are four variables declared for you at the top of the class that you can use throughout your exercise. You must fill in your API_KEY:

```java
public class App {

    public static final String API_URL = "https://te-pgh-api.azurewebsites.net/api/auctions";
    public static final String API_KEY = "";
    public static RestTemplate restTemplate = new RestTemplate();
    private static Scanner scanner;

    // ...

}
```

### List all auctions

In the `listAllAuctions()` method, find the comment `//api code here`. Add code here to:

- Use the RestTemplate to request all auctions and save them into an array of Auctions
- Replace the current return statement to return the array of auctions

Once you've done this, run the unit tests. After the test `listAllAuctions()` passes, you can run the application. If you select option 1 on the menu, you'll see the ID, title, and current bid for each auction.

### List details for a specific auction

In the `listDetailsForAuction()` method, find the `//api code here` comment. Add code here to:

- Use RestTemplate to request a specific auction by ID
- Return the single auction

Once you've done this, run the unit tests. After the test `listDetailsForAuction()` passes, you can run the application. If you select option 2 on the menu, and enter an ID of one of the auctions, you'll see the full details for that auction.

### Find auctions with a specified term in the title

In the `getByTitle(String title)` method, find the `//api code here` comment. Add code here to:

- Use RestTemplate to request all auctions which match the `title` parameter and save them into an array of Auctions.
- The API URL requires a query string with the key named `title_like` and the value of the `title` parameter. This requests the server to search for auctions that have a title containing the value in the `title` parameter.
- Replace the current return statement to return the array of auctions. Return null if no auctions were found.

Once you've done this, run the unit tests. After the tests `findAuctionsSearchTitle()` and `findAuctionsSearchTitleNotFound()` pass, run the application. If you select option 3 on the menu, and enter a string, like `watch`, you'll see the ID, title, and current bid for each auction that matches.

### Find auctions below a specified price

The final method, `getByPrice(double price)`, also uses a query string. The parameter key is `currentBid_lte` and has the value of the `price` parameter. This requests the server to search for auctions that have a `currentBid` that's **L**ess **T**han or **E**qual to the value you supply.

Find the `//api code here` comment and add code here to:

- Use RestTemplate to request all auctions which are less than or equal to the `price` parameter and save them into an array of Auctions.
- The API URL requires a query string with the key named `currentBid_lte` and the value of the `price` parameter. This requests the server to search for auctions that have a price less than or equal to the value in the `title` parameter.
- Replace the current return statement to return the array of auctions. Return null if no auctions were found.

Once you've done this, run the unit tests. After the tests `findAuctionsSearchPrice()` and `findAuctionsSearchPriceNotFound()` pass, run the application. If you select option 4 on the menu and enter a number, like `150`, you'll see the ID, title, and current bid for each auction that matches.

Since the value is a `double`, you can enter a decimal value, too. Try entering `125.25`, and then `125.20`, and observe the differences between the two result sets. The "Mad-dog Sneakers" don't appear in the second list because the current bid for them is `125.23`.
