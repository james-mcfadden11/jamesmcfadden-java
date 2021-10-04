package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSearch {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		System.out.println("What is the fully qualified name of the file that should be searched?");
		String filePath = input.nextLine();
		System.out.println("What is the search word you are looking for?");
		String wordToSearch = input.nextLine();

		System.out.println("Should the search be case sensitive? (Y/N)");
		String caseSensitive = input.nextLine();
		while (!caseSensitive.equals("Y") && !caseSensitive.equals("N")) {
			System.out.println("Please enter a valid response");
			System.out.println("Should the search be case sensitive? (Y/N)");
			caseSensitive = input.nextLine();
		}
		boolean areResultsCaseSensitive = (caseSensitive.equals("Y"));

		// access file
		File file = new File(filePath);

		// scan file for wordToSearch
		try (Scanner fileScanner = new Scanner(file)) {
			int lineNumber = 1;
			// iterate through entire text file
			while (fileScanner.hasNextLine()) {
				String line = fileScanner.nextLine();
				// regular expression to ignore non-letter characters for search purposes ("Who..." example)
				String[] splitLine = line.replaceAll("[^a-zA-Z ]", "").split(" ");
				// evaluate each line in txt file for wordToSearch
				// print line number and line if there is a match
				for (String word : splitLine) {
					// accounting for case-sensitive situation
					if (!areResultsCaseSensitive) {
						word = word.toLowerCase();
						wordToSearch = wordToSearch.toLowerCase();
					}
					// alternatively, could be word.startsWith(wordToSearch)
					// for a more inclusive "ctrl+f"-style search
					if (word.equals(wordToSearch)) {
						System.out.println(lineNumber + ". " + line);
						break;
					}
				}
				lineNumber++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("The file was not found!");
		}
	}
}
