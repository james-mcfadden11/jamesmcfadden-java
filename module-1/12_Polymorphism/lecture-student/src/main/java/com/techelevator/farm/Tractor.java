package com.techelevator.farm;

public class Tractor implements Singable, Sellable {


    @Override
    public double getPrice() {
        return 50;
    }

    @Override
    public String sing() {
        return null;
    }

    @Override
    public String someOtherThing() {
        return null;
    }
}
