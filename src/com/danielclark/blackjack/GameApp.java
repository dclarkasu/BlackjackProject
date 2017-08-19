package com.danielclark.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		game.dealerTurn();
		
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
//		for (Card c : deck.getCards()) {
////		System.out.println(c.getR() + " of " + c.getS() + " " + c.getR().getValue()); // prints out whole deck
//		}
		p.getHand().addCard(deck.dealCard());
		p.getHand().addCard(deck.dealCard());
		showPlayerHand();
		int playerHandValue = valueOfPlayerHand();
		System.out.println("Player's Hand: " + playerHandValue+ "\n");

		dealer.add(deck.dealCard());
		dealer.add(deck.dealCard());
		showDealerHand("ONE");
		int dealerHandValue = valueOfDealerHand();
//		System.out.println("Dealer's Hand: " + dealerHandValue + "\n");
		checkHandValue(playerHandValue, dealerHandValue);
//		System.out.println(deck.getCards().size());
		
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
			checkHandValue(playerHandValue, 0);
//			System.out.println(deck.getCards().size()); // prints size of deck after each hit
		}
		if (choice.equalsIgnoreCase("stay")) {
			dealerTurn();
		}
		} while(choice.equalsIgnoreCase("hit"));

	}
	
	
	public void checkHandValue(int playerHandValue, int dealerHandValue) {
		if(playerHandValue > 21) {
			System.out.println(p.getName() + " You have busted. House wins, game over");
			System.exit(0);
		}
		else if(playerHandValue == 21) {
			System.out.println(p.getName() + " Congratulations! You won.");
			System.exit(0);
			//add money to player wallet
		}
		if(dealerHandValue > 21) {
			System.out.println("Dealer," + " You have busted. Player wins, game over");
			System.exit(0);
		}
		else if(dealerHandValue == 21) {
			System.out.println("Dealer," + " Congratulations! You won.");
			System.exit(0);
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
			System.out.println("Dealer hand: " + dealer.get(0));
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
	
	public void dealerTurn() {
		int dealerHandValue = valueOfDealerHand();
		while (valueOfDealerHand() <= 17) {
			dealer.add(deck.dealCard());
			showDealerHand("ALL");
			dealerHandValue = valueOfDealerHand();
			System.out.println("Dealer's Hand: " + dealerHandValue + "\n");
			checkHandValue(0, dealerHandValue);
		}
		checkHandValue(0, dealerHandValue);
	}

}


















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
