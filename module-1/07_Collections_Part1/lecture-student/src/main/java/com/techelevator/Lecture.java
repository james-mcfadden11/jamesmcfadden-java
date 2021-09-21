package com.techelevator;

import java.util.*;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("####################");
		System.out.println("       LISTS");
		System.out.println("####################");

		List<String> names = new ArrayList<String>();
		names.add("Frank");
		names.add("Daniel");
		names.add("Reshmi");
		names.add("William");
		names.add("Josh");
		names.add("Grant");

		System.out.println("####################");
		System.out.println("Lists are ordered");
		System.out.println("####################");

		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}

		System.out.println("####################");
		System.out.println("Lists allow duplicates");
		System.out.println("####################");

		names.add("Grant");
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

		System.out.println(names.remove(6));
		System.out.println("-----------------------------");
		for (int i = 0; i < names.size(); i++) {
			System.out.println(names.get(i));
		}

		System.out.println("####################");
		System.out.println("Find out if something is already in the List");
		System.out.println("####################");

		if (names.contains("Grant")) {
			System.out.println("contains Grant");
		} else {
			System.out.println("does not contain grant");
		}

		System.out.println("####################");
		System.out.println("Find index of item in List");
		System.out.println("####################");

		System.out.println(names.indexOf("Walt"));

		System.out.println("####################");
		System.out.println("Lists can be turned into an array");
		System.out.println("####################");

		String[] namesArray = new String[names.size()];
		for (int i = 0; i < names.size(); i++) {
			namesArray[i] = names.get(i);
		}

		String[] namesArray2 = new String[names.size()];
		names.toArray(namesArray2);

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

		for (String name : names) {
			System.out.println(name);
		}

		System.out.println("####################");
		System.out.println("       List of Integers");
		System.out.println("####################");
		System.out.println();

		List<Integer> numbers = new ArrayList<>();

		numbers.add(1);
		numbers.add(5);
		numbers.add(10);

		for (Integer number : numbers) {
			System.out.println(number);
		}

		System.out.println("####################");
		System.out.println("       Queues and stacks");
		System.out.println("####################");
		System.out.println();

		Queue<String> coffeeLine = new LinkedList<>();
		coffeeLine.offer("Frank");
		coffeeLine.offer("Walt");
		coffeeLine.offer("Vineeth");
		coffeeLine.offer("Denny");
		coffeeLine.offer("Paige");

		String whosOnFirst = coffeeLine.peek();

		while (coffeeLine.size() > 0) {
			String nextUp = coffeeLine.poll();
			// do something
			System.out.println(nextUp);
		}

		System.out.println("-------------------------------------");

		Stack<String> urls = new Stack<>();
		urls.push("techelevator.com");
		urls.push("google.com");
		urls.push("espn.com");

//		System.out.println(urls.peek());

		// different from while loop result
		for (String theUrl : urls) {
			String nextUp = theUrl;
			System.out.println(nextUp);
		}

		while (!urls.isEmpty()) {
			String nextUp = urls.pop();
			// do something
			System.out.println(nextUp);
		}

	}
}
