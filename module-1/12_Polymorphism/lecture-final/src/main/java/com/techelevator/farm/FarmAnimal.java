package com.techelevator.farm;

public class FarmAnimal implements ExtraSingable {
	private String name;
	private String sound;

	public FarmAnimal(String name, String sound) {
		this.name = name;
		this.sound = sound;
	}

	public String getName() {
		return name;
	}

	public String getSound() {
		return sound;
	}

	@Override
	public String sing() {
		return null;
	}

	@Override
	public String moreSinging() {
		return "";
	}
}