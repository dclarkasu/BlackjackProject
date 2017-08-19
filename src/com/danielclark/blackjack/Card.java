package com.danielclark.blackjack;

public class Card {
	private Rank r;
	private Suit s;
	
	
	public Card(Rank r, Suit s) {
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[r=");
		builder.append(r);
		builder.append(", s=");
		builder.append(s);
		builder.append("]");
		return builder.toString();
	}
	
	

	//card.getrank().getvalue();
}
