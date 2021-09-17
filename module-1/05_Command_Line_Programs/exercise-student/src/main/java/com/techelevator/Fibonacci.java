package com.techelevator;

import java.util.Scanner;

public class Fibonacci {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a number you'd like to see the Fibonacci Sequence up to: ");
		String maxString = scanner.nextLine();
		int maxNumber = Integer.parseInt(maxString);

		int[] fibSeq = {0, 1};
		int numberToAdd = fibSeq[fibSeq.length - 1] + fibSeq[fibSeq.length - 2];

		while (numberToAdd <= maxNumber) {
			int[] newFibSeq = new int[fibSeq.length + 1];

			for (int i = 0; i < fibSeq.length; i++) {
				newFibSeq[i] = fibSeq[i];
			}

			newFibSeq[newFibSeq.length - 1] = numberToAdd;
			fibSeq = newFibSeq;
			numberToAdd = fibSeq[fibSeq.length - 1] + fibSeq[fibSeq.length - 2];
		}

		String result = "";
		for (int i = 0; i < fibSeq.length - 1; i++) {
			result += fibSeq[i];
			result += ", ";
		}

		System.out.println(result + fibSeq[fibSeq.length - 1]);
	}

}
