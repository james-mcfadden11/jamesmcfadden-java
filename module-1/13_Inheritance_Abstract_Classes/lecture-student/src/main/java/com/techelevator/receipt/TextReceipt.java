package com.techelevator.receipt;

import java.util.List;

public class TextReceipt extends Receipt {
    public TextReceipt(String storeName, List<String> itemsPurchased) {
        super(storeName, itemsPurchased);
    }

    @Override
    String printHeading() {
        return "welcome to the store";
    }

    @Override
    String printLineItems() {
        String result = "";
        for (String item : getItemsPurchased()) {
            result += item;
        }
        return result;
    }

    @Override
    String printTotal() {
        return null;
    }
}
