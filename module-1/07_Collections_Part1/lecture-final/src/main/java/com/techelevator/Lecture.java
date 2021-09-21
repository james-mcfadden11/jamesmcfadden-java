package com.techelevator;


import java.util.*;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");

		List<String> names = new ArrayList<>();
		names.add("Frank");
		names.add("Daniel");
		names.add("reshmi");
		names.add("William");
		names.add("Josh");
		names.add("grant");


		System.out.println("####################");
		System.out.println("Lists are ordered");
		System.out.println("####################");

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}


		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");

		names.add("grant");

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}


		System.out.println("####################");
		System.out.println("Lists allow elements to be inserted in the middle");
		System.out.println("####################");

		names.add(1, "Walt");

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}


		System.out.println("####################");
		System.out.println("Lists allow elements to be removed by index");
		System.out.println("####################");

		names.remove(6);

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}


		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");

		if (names.contains("Grant")) {
			System.out.println("Contains Grant!");
		} else if (names.contains("grant")) {
			System.out.println("Contains grant!");
		} else {
			System.out.println("Does not contain grant or Grant!");
		}


		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");

		int indexOfWalt = names.indexOf("Walt");
		System.out.println("Index of Walt: " + indexOfWalt);


		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");

		String[] namesArray = new String[names.size()];
		for (int i = 0; i < names.size(); i++) {
			namesArray[i] = names.get(i);
		}


		String[] namesArray2 = names.toArray(new String[0]);


		System.out.println("####################");
		System.out.println("Lists can be sorted");
		System.out.println("####################");

		Collections.sort(names);

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}

		System.out.println("####################");
		System.out.println("Lists can be reversed too");
		System.out.println("####################");

		Collections.reverse(names);

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}

		System.out.println("####################");
		System.out.println("       FOREACH");
		System.out.println("####################");
		System.out.println();

		for (String blah : names) {
			System.out.println(blah);
		}

		System.out.println("####################");
		System.out.println("       List of Integers");
		System.out.println("####################");
		System.out.println();

		/*

			int ---- > Integer
			double ---- > Double
			char ---->  Character
			boolean ---> Boolean

		 */
		List<Integer> numbers = new ArrayList<>();

		numbers.add(1);
		numbers.add(5);
		numbers.add(10);

		for (int i = 0; i< numbers.size(); i++) {
			System.out.println(numbers.get(i));
		}

		for (Integer num : numbers) {
			System.out.println(num);
		}

		Integer x = null;
		int y;


		System.out.println("####################");
		System.out.println("       Queues and Stacks ");
		System.out.println("####################");
		System.out.println();


		Queue<String> coffeeLine = new LinkedList<>();

		coffeeLine.offer("Frank");
		coffeeLine.offer("Walt");
		coffeeLine.offer("vineeth");
		coffeeLine.offer("Denny");
		coffeeLine.offer("Paige");

		String whoIsOnFirst = coffeeLine.peek();

		while (coffeeLine.size() > 0) {
			String nextUp = coffeeLine.poll();
			// do work
			System.out.println(nextUp);
		}


		Stack<String> urls = new Stack<>();

		urls.push("https://techelevator.com");
		urls.push("https://google.com");
		urls.push("https://espn.com");

		for (String theUrl : urls) {
			System.out.println(theUrl);
		}

		String whoIsOnTop = urls.peek();

		while (!urls.isEmpty()) {
			String nextUp = urls.pop();
			// do work
			System.out.println(nextUp);
		}



		/*
			Queue

			Frank Walt vineeth Denny Paige William





		 */
	}
}
