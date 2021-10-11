package com.techelevator.receipt;

import java.util.List;

public class HtmlReceipt extends Receipt {

    public HtmlReceipt(String storeName, List<String> itemsPurchased) {
        super(storeName, itemsPurchased);
    }

    @Override
    String printHeading() {
        return "<h1>welcome to "+ getStoreName() + " store</h1>";
    }

    @Override
    String printLineItems() {
        String result = "";
        for (String item : getItemsPurchased()) {
            result += "<tr>" + item + "</tr>";
        }
        return result;
    }

    @Override
    String printTotal() {
        return null;
    }

}
