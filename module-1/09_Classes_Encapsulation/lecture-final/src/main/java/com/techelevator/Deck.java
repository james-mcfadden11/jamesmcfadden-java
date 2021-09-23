package com.techelevator;

import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class Deck {
    private Stack<Card> listOfCards = new Stack<>();

    public Deck() {

    }

    public Deck(String[] suits, int[] ranks) {
        for (int i = 0; i < suits.length; i++) {
            Card c = new Card(ranks[i], suits[i]);
            listOfCards.push(c);
        }
    }

    public Deck(List<Card> cards) {
        listOfCards = new Stack<>();
        listOfCards.addAll(cards);
    }

    public Card dealCard() {
        if (listOfCards.isEmpty()) {
            return null;
        }

        return listOfCards.pop();
    }

    public void shuffle() {
        Collections.shuffle(listOfCards);
    }

    public int size() {
        return this.listOfCards.size();
    }
}
