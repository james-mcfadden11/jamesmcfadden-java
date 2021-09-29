package com.techelevator.vehicle;

public abstract class Car extends Vehicle {

    public Car(int wheelCount) {
        super(wheelCount);
    }

    @Override
    public void accelerate() {
        System.out.println("Pedal to metal!");
    }

    public abstract String getFuelType();

}
