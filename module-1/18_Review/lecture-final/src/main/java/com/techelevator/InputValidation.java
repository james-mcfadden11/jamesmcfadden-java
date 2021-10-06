package com.techelevator;

import java.util.Scanner;

public class InputValidation {

	public static void main(String[] args)  {

		while(true) {
			System.out.println("Please choose a menu option (1 or 2):");
			System.out.println("1) Say Hello");
			System.out.println("2) Say Goodbye");
			System.out.println("3) Exit");

			Scanner input = new Scanner(System.in);
			String line = input.nextLine();
			int menuOptionChosen = -1;

			try {
				menuOptionChosen = Integer.parseInt(line);

			} catch (NumberFormatException e) {
				System.out.println("Invalid choice");
				continue;
			}

			switch (menuOptionChosen) {
				case 1:
					System.out.println("Hello!");
					break;
				case 2:
					System.out.println("Goodbye!");
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Invalid choice");
			}
		}


	}

}
