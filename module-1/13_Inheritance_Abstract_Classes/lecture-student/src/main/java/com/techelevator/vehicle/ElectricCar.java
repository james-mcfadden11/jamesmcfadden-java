package com.techelevator.vehicle;

public class ElectricCar extends Car {

    public ElectricCar(int wheelCount) {
        super(wheelCount);
    }

    @Override
    public String getFuelType() {
        return "electric";
    }

}
