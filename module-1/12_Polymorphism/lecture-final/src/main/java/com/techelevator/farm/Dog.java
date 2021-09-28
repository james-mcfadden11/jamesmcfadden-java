package com.techelevator.farm;

public class Dog extends FarmAnimal {
    public Dog() {
        super("Fido", "woof!");
    }

    @Override
    public String getSound() {
        return "BARK BARK!";
    }

    public void bark() {
        System.out.println("Bark!");
    }
}
