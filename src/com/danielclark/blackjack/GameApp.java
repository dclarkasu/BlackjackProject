package com.danielclark.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Dealer ArrayList. .add to give him a card. .get to get his cards

public class GameApp {
	private Player p = new Player();
	private Deck deck = new Deck();
	private List<Card> dealer = new ArrayList<>();

	public static void main(String[] args) {
		GameApp game = new GameApp();
		Scanner kb = new Scanner(System.in);
		
		game.welcome(kb);
		game.run();
		game.hitOrStay(kb);
		
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
	
	public void run() {
		deck = initializeDeck();
		for (Card c : deck.getCards()) {
//		System.out.println(c.getR() + " of " + c.getS() + " " + c.getR().getValue()); // prints out whole deck
		}
		p.getHand().addCard(deck.dealCard());
		p.getHand().addCard(deck.dealCard());
		dealer.add(deck.dealCard());
		dealer.add(deck.dealCard());
//		System.out.println(deck.getCards().size());
		
		int playerHandValue = valueOfPlayerHand();
		int dealerHandValue = valueOfDealerHand();
//		for (Card c : p.getHand().getCards()) { // printing out the player's hand. Have to not only get Hand object but his actual cards
//			System.out.println(c);
//			playerHandValue = playerHandValue + c.getR().getValue();
//		}
		
		showPlayerHand();
		showDealerHand("ONE");
		
		System.out.println("Player's Hand: " + playerHandValue);
		if(playerHandValue == 21) {
			
		}
//		return playerHandValue;
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
	
	public void hitOrStay(Scanner kb) {
		// Need to add actions for getting blackjack or busting
		int playerHandValue = valueOfPlayerHand();
		String choice;
		do {
		System.out.println("Would you like to hit or stay?");
		choice = kb.next();
		if (choice.equalsIgnoreCase("hit")) {
			Card c = p.getHand().addCard(deck.dealCard());
			System.out.println(c);
				playerHandValue = playerHandValue + c.getR().getValue();
			System.out.println("Player's Hand: " + playerHandValue);
			System.out.println(deck.getCards().size()); // prints size of deck after each hit
		}
		if (choice.equalsIgnoreCase("stay")) {
			// playerHandValue will stay the same
			// turn will change	
		}
		} while(choice.equalsIgnoreCase("hit"));
		
		//while the dealers value is <= 17
	}
	
//	public void dealerAction() {
//		dealer
//		
//	}
	
	public void checkHandValue(int playerHandValue) {
		if(playerHandValue > 21) {
			System.out.println("You have busted. Game over");
		}
		if(playerHandValue == 21) {
			System.out.println("Congratulations! You won.");
			//add money to player wallet
		}
	}
	
	public void showPlayerHand() {
		for (Card card : p.getHand().getCards()) {
			System.out.println(card);
		}
	}
	
	public void showDealerHand(String amount) {
		if(amount.equals("ONE")) {
			System.out.println(dealer.get(0));
		}
		else if(amount.endsWith("ALL")) {
			for (Card card : dealer) {
				System.out.println(card);
			}
		}
	}
	
	public int valueOfPlayerHand() {
		int val = 0;
		for (Card card : p.getHand().getCards()) {
			val = val + card.getR().getValue();
		}
		return val;
	}
	
	public int valueOfDealerHand() {
		int val = 0;
		for (Card card : dealer) {
			val = val + card.getR().getValue();
		}
		return val;
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
