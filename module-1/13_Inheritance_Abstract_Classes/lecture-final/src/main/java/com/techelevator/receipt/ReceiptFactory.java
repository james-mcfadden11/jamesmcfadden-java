package com.techelevator.receipt;

import java.util.List;

public class ReceiptFactory {

    public static Receipt create(String usersPreference, String storeName, List<String> itemsPurchased) {

        if (usersPreference.equalsIgnoreCase("text")) {
            // create a TextReceipt
            return new TextReceipt(storeName, itemsPurchased);
        } else if(usersPreference.equalsIgnoreCase("html")) {
            // create an HtmlReceipt
            return new HtmlReceipt(storeName, itemsPurchased);
        }

        throw new IllegalArgumentException("Invalid choice");
        // throw an exception (invalid choice)
    }
}
