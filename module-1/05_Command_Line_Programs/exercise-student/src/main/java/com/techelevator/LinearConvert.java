package com.techelevator;

import java.util.Scanner;

public class LinearConvert {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		// take user input
		String lengthStr;
		Double lengthToConvert;
		System.out.print("Please enter the length to convert: ");
		// validate
		while (true) {
			lengthStr = scanner.nextLine();
			lengthToConvert = Double.parseDouble(lengthStr);
			if (lengthToConvert >= 0) {
				break;
			} else {
				System.out.print("Please enter a valid length greater than 0: ");
			}
		}

		// take user input
		String unitStr;
		System.out.print("Please enter the units (f = feet or m = meters): ");
		// validate
		while (true) {
			unitStr = scanner.nextLine();
			if (unitStr.equals("f") || unitStr.equals("m")) {
				break;
			} else {
				System.out.print("Please enter a valid unit (f = feet or m = meters): ");
			}
		}

		switch (unitStr) {
			case "f":
				double meters = lengthToConvert * 0.3048;
				System.out.println(lengthToConvert + " feet is equal to " + Math.round(meters * 100.0) / 100.0 + " meters.");
				break;
			case "m":
				double feet = lengthToConvert * 3.2808399;
				System.out.println(lengthToConvert + " meters is equal to " + Math.round(feet * 100.0) / 100.0 + " feet.");
				break;
			default:
				// should not reach this point
				System.out.println("Something went wrong!");
				break;
		}
	}

}
