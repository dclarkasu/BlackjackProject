package com.danielclark.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private double betAmt;
	private List<Card> cards;

	public double getBetAmt() {
		return betAmt;
	}

	public void setBetAmt(double betAmt) {
		this.betAmt = betAmt;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	// this is for when the player hits
	public void addCard(Card card) {

		if (cards == null) {
			cards = new ArrayList<Card>();
			cards.add(card);
		} else {
			cards.add(card);
		}
	}
	
	public void resetHand() {
		cards = new ArrayList<Card>();
	}
	
	public void displayHand() {
		
	}

	// get value of cards in hand?
	//get betAmt?
}
