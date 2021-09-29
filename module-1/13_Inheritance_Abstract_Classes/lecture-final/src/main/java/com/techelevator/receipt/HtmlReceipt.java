package com.techelevator.receipt;

import java.util.List;

public class HtmlReceipt extends Receipt {
    public HtmlReceipt(String storeName, List<String> itemsPurchased) {
        super(storeName, itemsPurchased);
    }

    @Override
    public String printHeading() {
        return "<h1>Welcome to " + getStoreName() + "!</h1>";
    }

    @Override
    public String printLineItems() {
        String result = "<table>";

        for(String item : getItemsPurchased()) {
            result += "<tr><td>" + item + "</td></tr>";
        }

        result += "</table>";

        return result;
    }

    @Override
    public String printTotal() {
        return null;
    }
}
