package com.techelevator;

import java.util.*;

public class Lecture {

	public static void main(String[] args) {

		System.out.println("####################");
		System.out.println("        MAPS");
		System.out.println("####################");
		System.out.println();

		Map<String, String> namesToZip = new LinkedHashMap<>();

		namesToZip.put("David", "44120");     // David -> 10
		namesToZip.put("Dan", "44124");       // Dan ->   6
		namesToZip.put("Elizabeth", "44012"); // Elizabeth -> 15

		String davidsZip = namesToZip.get("David");

		System.out.println("David's zip code is: " + davidsZip);

		for (Map.Entry<String, String> entry : namesToZip.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();

			System.out.println("Key: " + key + " Value: " + value);
		}

		for (String key : namesToZip.keySet()) {
			String value = namesToZip.get(key);

			System.out.println("Key: " + key + " Value: " + value);
		}

		if (namesToZip.containsKey("Elizabeth")) {
			System.out.println("Yes, Elizabeth is in our Map!");
		}

		namesToZip.put("David", "12345");

		for (String key : namesToZip.keySet()) {
			String value = namesToZip.get(key);

			System.out.println("Key: " + key + " Value: " + value);
		}

		String previousValue = namesToZip.remove("Elizabeth");
		String anotherPreviousValue = namesToZip.remove("Bob");


		for (String key : namesToZip.keySet()) {
			String value = namesToZip.get(key);

			System.out.println("Key: " + key + " Value: " + value);
		}


		System.out.println("The size of our map is: " + namesToZip.size());


		/*
			SETS
		 */
		Set<Integer> numbers = new HashSet<>();

		boolean didAdd = numbers.add(1);
		numbers.add(5);
		numbers.add(10);
		numbers.add(5);

		for (int myNumber : numbers) {
			System.out.println(myNumber);
		}

		System.out.println("Size: " + numbers.size());

		boolean doesContainFive = numbers.contains(5);

		if(doesContainFive) {
			System.out.println("Yep! Contains 5");
		}

		if(numbers.contains(100)) {
			System.out.println("Yep! Contains 100");
		}

		numbers.remove(5);

		System.out.println("Size: " + numbers.size());

		numbers.clear(); // remove everything

		Set<String> names = new HashSet<>();

		Set<String> otherNames = new HashSet<>(Set.of("Daniel", "Bobby", "Alex", "James"));
		Map<String,String> namesAndZipsAgain = new HashMap<>(Map.of("David", "44120", "Elizabeth", "12345"));

		names.add("Daniel");
		names.add("Walt");
		names.add("Dennis");
		names.add("dennis");
		names.add("Daniel");

		for (String theName : names) {
			System.out.println(theName);
		}


		/*

			Build a dictionary containing Java and C#

			How can I order my map or set?


			Definitions -> Set@222 { Why does it even exist? Why are you here?, The inferior language, a.k.a. D FLAT,  }

			Map

			Key         Value
			"Java" -->  Set@111 { "Joseph's favorite language", "Alex's morning coffee" }
			"C#"   -->  Set@222 { Why does it even exist? Why are you here?, The inferior language, a.k.a. D FLAT,  }


		 */

		Map<String, Set<String>> dictionary = new HashMap<>();

		Set<String> definitions = new HashSet<>();
		definitions.add("Joseph's favorite language.");
		definitions.add("Alex's morning coffee");

		dictionary.put("Java", definitions);

		Set<String> theJavaDefinitions = dictionary.get("Java");

		definitions = new HashSet<>();

		definitions.add("The inferior language");
		definitions.add("Why does it even exist? Why are you here?");
		definitions.add("a.k.a. D FLAT");

		dictionary.put("C#", definitions);

		/*
		   Java -> Joseph's Favorite Language
		   Java -> Alex's morning coffee
		   C# -> The inferior language, Why does it even exist? Why are you here?, a.k.a. D FLAT
		 */

		for (String key : dictionary.keySet()) {

			Set<String> theDefinitions = dictionary.get(key);

			System.out.print(key + " -> ");

			for (String value : theDefinitions) {
				System.out.print(value + ", ");
			}

			System.out.println();
		}


		/*

			Algorithmic Complexity

			O(1)
			O(N)
			O(N^2)
		 */

		int x = 1;
		int[] array = { 1, 2, 5, 10, 6 };

		int y = array[2];

		for (int someNumber : array) {
			if (someNumber == 11) {
				System.out.println("Found it!");
				break;
			}
		}


		for (int i = 0; i < array.length; i++) { // N

			for (int j = i + 1; j < array.length; j++) { // N

				if (array[i] == array[j]) {

				}
			}
		}

		// N^2

	}


}
