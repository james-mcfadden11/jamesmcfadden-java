package com.techelevator;


public class Lecture {

	public static void main(String[] args) {

		House myHouse = new House();
		


		System.out.println("************************************");
		System.out.println("****** MAKING A STRING OBJECT ******");
		System.out.println("************************************");

		/* The String class gets special treatment in the Java language.  One
		 * example of this is that there is a literal representation of a
		 * String (i.e. characters appearing between two double quotes.  This
		 * is not the case for most classes */

		String name = "Walt";

		/* create an new instance of String using a literal */

		char[] nameArray = { 'W', 'a', 'l', 't' };

		String newName = new String(nameArray);

		
		System.out.println();
		System.out.println("******************************");
		System.out.println("****** MEMBER METHODS ******");
		System.out.println("******************************");
		System.out.println();

		boolean doesEndWithLT = newName.endsWith("lt");
		System.out.println("Walt ends with lt? " + doesEndWithLT);


		boolean doesStartWith = newName.startsWith("Wa");
		System.out.println("Walt starts with Wa? " + doesStartWith);

		int indexOfA = newName.indexOf('a');
		System.out.println("Index of a in Walt: " + indexOfA);

		int indexOfE = "Tech Elevator".indexOf('e');
		System.out.println("Index of e in Tech Elevator: " + indexOfE);

		int indexOfLastE = "Tech Elevator".lastIndexOf('e');
		System.out.println("Index of last e in Tech Elevator: " + indexOfLastE);

		int indexNotFound = "Tech".indexOf('z');
		System.out.println("Index of z in Tech: " + indexNotFound);

		int indexOfCH = "Tech".indexOf("ch");
		System.out.println("Index of ch in Tech: " + indexOfCH);

		System.out.println("Walt is of length: " + name.length());

		int[] array = { 1, 2, 3 };
		System.out.println("Array length: " + array.length);

		String techElevator = "Tech Elevator";

		System.out.println(techElevator.substring(1));
		System.out.println(techElevator.substring(1, techElevator.length()));

		System.out.println(techElevator.toLowerCase());
		System.out.println(techElevator.toUpperCase());

		System.out.println("   Tech Elevator     sadf  ".trim());

		System.out.println(techElevator.replace('e', 'j'));

		System.out.println(techElevator.contains("Elevator"));

		System.out.println(techElevator.charAt(3));

		for (int i = 0; i < techElevator.length(); i++) {
			System.out.println(techElevator.charAt(i));
		}



		/* Other commonly used methods:
		 *
		 * endsWith
		 * startsWith
		 * indexOf
		 * lastIndexOf
		 * length
		 * substring
		 * toLowerCase
		 * toUpperCase
		 * trim
		 */

		System.out.println();
		System.out.println("**********************");
		System.out.println("****** EQUALITY ******");
		System.out.println("**********************");
		System.out.println();



		/* Double equals will compare to see if the two variables, hello1 and
		 * hello2 point to the same object in memory. Are they the same object? */

		String hello1 = "Hello";
		String hello2 = "Hello";

		if (hello1 == hello2) {
			System.out.println("They are equal!");
		} else {
			System.out.println(hello1 + " is not equal to " + hello2);
		}

		String hello3 = hello1;
		if (hello1 == hello3) {
			System.out.println("hello1 is the same reference as hello3");
		}



		/* So, to compare the values of two objects, we need to use the equals method.
		 * Every object type has an equals method */

//		char[] helloChars = { 'H', 'e', 'l', 'l', 'o' };
		hello1 = new String("Hello");

		if (hello1 == hello2) {
			System.out.println("They are equal with ==");
		} else if(hello1.equals(hello2)) {
			System.out.println("They are equal with .equals");
		} else {
			System.out.println(hello1 + " is not equal to " + hello2);
		}



	}
}
