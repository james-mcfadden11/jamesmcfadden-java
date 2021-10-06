package com.techelevator;

import java.io.*;
import java.util.Scanner;

public class FizzWriter {
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		System.out.println("Please enter the file path for fizzbuzz output:");
		String filePath = userInput.nextLine();

		File file = new File(filePath);

		try (PrintWriter printWriter = new PrintWriter(file)) {
			for (int lineNumber = 1; lineNumber <= 300; lineNumber++) {
				if (lineNumber % 3 == 0 && lineNumber % 5 == 0) {
					printWriter.println("FizzBuzz");
				} else if (lineNumber % 3 == 0) {
					printWriter.println("Fizz");
				} else if (lineNumber % 5 == 0) {
					printWriter.println("Buzz");
				} else {
					printWriter.println(lineNumber);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("file was not found");
		}

	}
}
