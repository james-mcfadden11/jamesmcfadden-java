package com.techelevator.farm;

import java.util.*;

public class OldMacdonald {
	public static void main(String[] args) {


		/*
			    Iterable
			    Collection
			List       Set
		 */


//		FarmAnimal fa = new FarmAnimal("The name", "The sound");

		Dog fido = new Dog();

		singOldMacdonald(fido);


		FarmAnimal[] farmAnimals = new FarmAnimal[] { new Cow(), new Chicken(), fido, new Rooster() };
		ExtraSingable[] singableFarmAnimals = new ExtraSingable[] { new Cow(), new Chicken() };

		List<Sellable> itemsToSell = new ArrayList<>();
		itemsToSell.add(new Rooster());
		itemsToSell.add(new Tractor());
		
		for (Sellable itemToSell : itemsToSell) {
			addToCart(itemToSell);
		}


//		ExtraSingable[] singableFarmAnimals = farmAnimals;


		for (FarmAnimal animal : farmAnimals) {
			singOldMacdonald(animal);
		}


		List<Chicken> chickens = new ArrayList<>();
		chickens.add(new Chicken());
		chickens.add(new Rooster());

		System.out.println("/** ------------------------------- **/");
		System.out.println();

		List<FarmAnimal> farmAnimalsList = new ArrayList<>(List.of( new Cow(), new Chicken(), fido, new Rooster() ));
		Set<FarmAnimal> typesOfAnimals = new HashSet<>(farmAnimalsList);

		iterate(farmAnimalsList);
		iterate(typesOfAnimals);
	}

	public static void singOldMacdonald(FarmAnimal animal) {
		String name = animal.getName();
		String sound = animal.getSound();
		System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
		System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
		System.out.println("With a " + sound + " " + sound + " here");
		System.out.println("And a " + sound + " " + sound + " there");
		System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
		System.out.println();
	}

	public static void iterate(Collection<FarmAnimal> allAnimals) {


		for(FarmAnimal animal : allAnimals) {

			if(animal.getClass().equals(Dog.class)) {
				Dog theDog = (Dog) animal;
				theDog.bark();

			}

			String name = animal.getName();
			String sound = animal.getSound();
			System.out.println("Old MacDonald had a farm, ee, ay, ee, ay, oh!");
			System.out.println("And on his farm he had a " + name + ", ee, ay, ee, ay, oh!");
			System.out.println("With a " + sound + " " + sound + " here");
			System.out.println("And a " + sound + " " + sound + " there");
			System.out.println("Here a " + sound + " there a " + sound + " everywhere a " + sound + " " + sound);
			System.out.println();
		}
	}

	public static void addToCart(Sellable sellableItem) {
		System.out.println("The price of this item is: " + sellableItem.getPrice());
	}

}