package com.techelevator.farm;

public class Rooster extends Chicken implements Sellable, Singable {

    public Rooster() {
        super("Chanteclaire", "cockadoodledoo!");
    }

    @Override
    public double getPrice() {
        return 0;
    }
}
