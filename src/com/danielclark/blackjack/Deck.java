package com.danielclark.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> cards;

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public Card dealCard() {
		// dealing a card removes one from the deck each time
		return cards.remove(0);
	}
	
	public void shuffleCards() {
		Collections.shuffle(cards);
	}
	
	public void addCard(Card card) {

		if (cards == null) {
			cards = new ArrayList<Card>();
			cards.add(card);
		} else {
			cards.add(card);
		}
	}

	
	// cards left in deck?
    // deck change?

}
