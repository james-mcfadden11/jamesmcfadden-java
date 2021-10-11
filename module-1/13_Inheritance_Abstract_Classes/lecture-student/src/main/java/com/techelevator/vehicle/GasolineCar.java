package com.techelevator.vehicle;

public class GasolineCar extends Car {

    public GasolineCar(int wheelCount) {
        super(wheelCount);
    }

    @Override
    public String getFuelType() {
        return "gas";
    }

}
