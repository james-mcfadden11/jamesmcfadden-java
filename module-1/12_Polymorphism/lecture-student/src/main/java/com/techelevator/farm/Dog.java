package com.techelevator.farm;

public class Dog extends FarmAnimal {
    public Dog() {
        super("dog", "woof");
    }

    public void bark() {
        System.out.println("bark!");
    }

    @Override
    public String getSound() {
        return "Bark Bark";
    }

}
