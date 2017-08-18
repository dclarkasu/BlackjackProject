package com.danielclark.blackjack;

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


}
