package com.techelevator.farm;

public class Chicken extends FarmAnimal {

	public Chicken() {
		super("Chicken", "cluck!");
	}

	public Chicken(String name, String sound) {
		super(name, sound);
	}

	public void layEgg() {
		System.out.println("Chicken laid an egg!");
	}

}