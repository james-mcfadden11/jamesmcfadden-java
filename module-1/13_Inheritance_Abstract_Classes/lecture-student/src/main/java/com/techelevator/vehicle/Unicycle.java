package com.techelevator.vehicle;

public class Unicycle extends Vehicle {


    public Unicycle() {
        super(1);
    }

    @Override
    public void accelerate() {
        System.out.println("pedal fast and balance!");
    }

}
