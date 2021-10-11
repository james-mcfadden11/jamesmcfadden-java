package com.techelevator.farm;

public class Rooster extends Chicken implements Sellable {
    public Rooster() {
        super("Chanteclaire", "cockadoodledo");
    }

    @Override
    public double getPrice() {
        return 15;
    }


}
