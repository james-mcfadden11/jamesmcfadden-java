package com.techelevator.receipt;

import java.util.List;

// commonly implemented as static
public class ReceiptFactory {
    public Receipt create(String userPreference, String storeName, List<String> itemsPurchased) {
        if (userPreference.equalsIgnoreCase("text")) {
            // create a text receipt
            return new TextReceipt(storeName, itemsPurchased);
        } else if (userPreference.equalsIgnoreCase("html")) {
            // create an html receipt
            return new HtmlReceipt(storeName, itemsPurchased);
        }

        // throw an exception for invalid choice
        throw new IllegalArgumentException("Invalid choice");
    }
}
