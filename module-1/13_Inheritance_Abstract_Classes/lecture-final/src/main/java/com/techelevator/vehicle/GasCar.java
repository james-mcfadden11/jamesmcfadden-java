package com.techelevator.vehicle;

public class GasCar extends Car{


    public GasCar(int wheelCount) {
        super(wheelCount);
    }

    @Override
    public String getFuelType() {
        return "Gas";
    }
}
