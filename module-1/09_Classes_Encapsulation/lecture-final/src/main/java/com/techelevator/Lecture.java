package com.techelevator;

import java.util.*;

public class Lecture {

	public static void main(String[] args) {
		System.out.println("Hello World");

		Card twoOfSpades = new Card(2, "Spades");

		int rank = twoOfSpades.getRank();
		twoOfSpades.setRank(3);
		rank = twoOfSpades.getRank();

		Card threeOfSpades = new Card(3, "Spades");

		System.out.println(threeOfSpades);

		List<Card> deck = new ArrayList<>();
		deck.add(twoOfSpades);
		deck.add(threeOfSpades);

		Deck pokerDeck = new Deck(deck);


	}

}
