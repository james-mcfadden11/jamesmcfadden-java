package com.techelevator;

import java.util.HashSet;
import java.util.Set;

public class Lecture {

	public static void main(String[] args) {

		Set<Integer> uniqueNumbers = new HashSet<>();

		Set<String> names = new HashSet<>();

		uniqueNumbers.add(1);
		uniqueNumbers.add(10);
		uniqueNumbers.add(10);
		uniqueNumbers.add(100);

		names.add("Walt");
		names.add("Tom");
		names.add("Tom");



		Person walt = new Person("Walt", "Impellicceiri");
		Person tom = new Person("Tom", "Medvitz");

		walt.print();

		tom.print();

		printHelloWorld();

	}

	public static void printHelloWorld() {
		System.out.println("Hello World!");
	}

}
