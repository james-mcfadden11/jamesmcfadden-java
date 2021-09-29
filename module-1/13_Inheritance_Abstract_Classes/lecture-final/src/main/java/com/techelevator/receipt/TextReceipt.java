package com.techelevator.receipt;

import java.util.List;

public class TextReceipt extends Receipt {
    public TextReceipt(String storeName, List<String> itemsPurchased) {
        super(storeName, itemsPurchased);
    }

    @Override
    public String printHeading() {
        return "Welcome to " + getStoreName();
    }

    @Override
    public String printLineItems() {
        String result = "";

        for(String item : getItemsPurchased()) {
            result += item + "\n";
        }

        return result;
    }

    @Override
    public String printTotal() {
        return null;
    }
}
