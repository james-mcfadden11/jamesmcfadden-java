package com.techelevator;

import java.util.Scanner;

public class InputValidation {

	public static void main(String[] args)  {

		while (true) {
			System.out.println("choose a menu option: ");
			System.out.println("1 - say hello");
			System.out.println("2 - say goodbye");
			System.out.println("3 - exit");

			Scanner input = new Scanner(System.in);
			String line = input.nextLine();
			int menuOptionChosen;

			try {
				menuOptionChosen = Integer.parseInt(line);
			} catch (NumberFormatException e) {
				System.out.println("invalid choice");
				continue;
			}

			switch (menuOptionChosen) {
				case 1:
					System.out.println("hello");
					break;
				case 2:
					System.out.println("goodbye");
					break;
				case 3:
					System.out.println("exiting");
					System.exit(0);
				default:
					System.out.println("invalid choice");
			}
		}
	}
}
