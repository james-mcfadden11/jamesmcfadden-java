package com.techelevator;

import java.util.Scanner;

public class TempConvert {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// take user input
		System.out.print("Please enter the temperature to convert: ");
		String tempStr = scanner.nextLine();
		int tempToConvert = Integer.parseInt(tempStr);
		// validate?

		// take user input
		String unitStr;
		System.out.print("Please enter the units (f = Fahrenheit or c = Celsius): ");
		// validate
		while (true) {
			unitStr = scanner.nextLine();
			if (unitStr.equals("f") || unitStr.equals("c")) {
				break;
			} else {
				System.out.print("Please enter a valid unit (f = Fahrenheit or c = Celsius): ");
			}
		}

		switch (unitStr) {
			case "f":
				double celsius = (tempToConvert - 32) / 1.8;
				System.out.println(tempToConvert + " Fahrenheit is equal to " + Math.round(celsius) + " Celsius.");
				break;
			case "c":
				double fahr = tempToConvert * 1.8 + 32;
				System.out.println(tempToConvert + " Celsius is equal to " + Math.round(fahr) + " Fahrenheit.");
				break;
			default:
				// should not reach this point
				System.out.println("Something went wrong!");
				break;
		}
	}

}
