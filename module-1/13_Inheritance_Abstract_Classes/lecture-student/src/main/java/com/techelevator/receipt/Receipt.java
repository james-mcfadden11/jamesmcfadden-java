package com.techelevator.receipt;

import java.util.ArrayList;
import java.util.List;

public abstract class Receipt {
    private String storeName;
    private List<String> itemsPurchased;

    public Receipt(String storeName, List<String> itemsPurchased) {
        this.storeName = storeName;
        this.itemsPurchased = new ArrayList<>(itemsPurchased);
    }

    public String getStoreName() {
        return storeName;
    }

    public List<String> getItemsPurchased() {
        return itemsPurchased;
    }

    // default access modifiers for abstract methods
    abstract String printHeading();
    abstract String printLineItems();
    abstract String printTotal();

    public String printReceipt() {
        String heading = printHeading();
        String lineItems = printLineItems();
        String total = printTotal();
        return heading + lineItems + total;

    }



}
