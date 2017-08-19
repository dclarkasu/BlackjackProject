package com.danielclark.blackjack;

import java.util.Scanner;

// Dealer ArrayList. .add to give him a card. .get to get his cards

public class GameApp {
	private Player p = new Player();
	private Deck deck = new Deck();

	public static void main(String[] args) {
		GameApp game = new GameApp();
		Scanner kb = new Scanner(System.in);
		
		game.welcome(kb);
		int playerHandValue = game.run();
		game.hitOrStay(kb, playerHandValue);
		
	}
	
	public void welcome(Scanner kb) {
		System.out.println("Welcome to the Blackjack table");
		System.out.println("Are you ready to play (Y/N)");
		String choice = kb.next();
		if(choice.equalsIgnoreCase("y")) {
			System.out.println("Please enter your name: ");
			String name = kb.next();
			p.setName(name);
			
		} else {
			System.exit(0);
		}
	}
	
	public int run() {
		deck = initializeDeck();
		for (Card c : deck.getCards()) {
//		System.out.println(c.getR() + " of " + c.getS() + " " + c.getR().getValue()); // prints out whole deck
		}
		p.getHand().addCard(deck.dealCard());
		p.getHand().addCard(deck.dealCard());
//		System.out.println(deck.getCards().size());
		
		int playerHandValue = 0;
		for (Card c : p.getHand().getCards()) { // printing out the player's hand. Have to not only get Hand object but his actual cards
			System.out.println(c);
			playerHandValue = playerHandValue + c.getR().getValue();
		}
		System.out.println("Player's Hand: " + playerHandValue);
		return playerHandValue;
	}
	
	public Deck initializeDeck() {  // create deck first so we have somewhere to store Cards next
		
		for(Suit s : Suit.values()) {     // iterates through Suit enums
			for(Rank r : Rank.values()) { // gives values to cards
				Card c = new Card(r,s);  // calls Card constructor to create Card obj
				deck.addCard(c); 		// add newly created cards to deck
			}
		}
		deck.shuffleCards();
		return deck;
	}
	
	public void hitOrStay(Scanner kb, int playerHandValue) {
		System.out.println("Would you like to hit or stay?");
		String choice = kb.next();
		if (choice.equalsIgnoreCase("hit")) {
			p.getHand().addCard(deck.dealCard());
			for (Card c : p.getHand().getCards()) {
				System.out.println(c);
				playerHandValue = playerHandValue + c.getR().getValue();
			}
			System.out.println("Player's Hand: " + playerHandValue);
		}
	}

}

// ask player to hit or stay
// if hit, then get player hand, add a card, deal card
























//public int checkForAces(Player player) {
//
//
//int playerValue = 0;
//
//for (Card c : player.getHand().getCards()) {
//	playerValue = playerValue + c.getR().getValue();
//	
//}
//
//System.out.println(playerValue);
//for (Card c : player.getHand().getCards()) {
//	if (playerValue > 21 && c.getR().equals(Rank.ACE)) {
//		playerValue = playerValue - 10;
//	}
//}
//
//return playerValue;
//}
