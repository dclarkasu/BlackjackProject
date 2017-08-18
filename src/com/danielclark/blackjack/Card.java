package com.danielclark.blackjack;

public class Card {
	private Rank r;
	private Suit s;
	
	
	public Card(Rank r, Suit s) {
		super();
		this.r = r;
		this.s = s;
	}
	public Rank getR() {
		return r;
	}
	public void setR(Rank r) {
		this.r = r;
	}
	public Suit getS() {
		return s;
	}
	public void setS(Suit s) {
		this.s = s;
	}

	//card.getrank().getvalue();
}
