package com.danielclark.blackjack;

public enum Rank {
	TWO(2);

	int value;

	private Rank(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
