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
		Hand hand = new Hand();

		game.welcome(kb);

		do {
			game.run();
			game.hitOrStay(kb);
			if (game.valueOfPlayerHand() <= 21) {
				game.dealerTurn();
			}
			game.checkForWin();
			hand.resetHand();
		} while (game.playAgain(kb, hand));

	}

	public boolean playAgain(Scanner kb, Hand hand) {
		System.out.println("Would you like to play again?");
		String answer = kb.next();
		if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("ya")) {
			hand.resetHand();
			return true;
		} else {
			return false;
		}
	}

	public void welcome(Scanner kb) {
		System.out.println("Welcome to the Blackjack table");
		System.out.println("Are you ready to play (Y/N)");
		String choice = kb.next();
		if (choice.equalsIgnoreCase("y")) {
			System.out.println("Please enter your name: ");
			String name = kb.next();
			p.setName(name);

		}
	}

	public void run() {
		deck = initializeDeck();
		// for (Card c : deck.getCards()) {
		//// System.out.println(c.getR() + " of " + c.getS() + " " +
		// c.getR().getValue()); // prints out whole deck
		// }
		p.getHand().addCard(deck.dealCard());
		p.getHand().addCard(deck.dealCard());
		showPlayerHand();
		int playerHandValue = valueOfPlayerHand();
		System.out.println("Player's Hand: " + playerHandValue + "\n");

		dealer.add(deck.dealCard());
		dealer.add(deck.dealCard());
		showDealerHand("ONE");
		int dealerHandValue = valueOfDealerHand();
		// System.out.println("Dealer's Hand: " + dealerHandValue + "\n");
		checkForWin();
		// System.out.println(deck.getCards().size());

	}

	public Deck initializeDeck() { // create deck first so we have somewhere to store Cards next

		for (Suit s : Suit.values()) { // iterates through Suit enums
			for (Rank r : Rank.values()) { // gives values to cards
				Card c = new Card(r, s); // calls Card constructor to create Card obj
				deck.addCard(c); // add newly created cards to deck
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

				System.out.println("Player's Hand: " + valueOfPlayerHand());
				if (valueOfPlayerHand() > 21) {
					break;
				}
				// checkHandValue();
				// System.out.println(deck.getCards().size()); // prints size of deck after each
				// hit
			}
			if (choice.equalsIgnoreCase("stay")) {
				dealerTurn();
			}
		} while (choice.equalsIgnoreCase("hit"));

	}

	public void checkForWin() {

		int playerHandValue = valueOfPlayerHand();
		int dealerHandValue = valueOfDealerHand();

		if (p.getHand().getCards().size() == 2 && dealer.size() == 2) {
			if (playerHandValue == 21 && dealerHandValue != 21) {
				System.out.println("BlackJack Player Wins");
			} else if (playerHandValue != 21 && dealerHandValue == 21) {
				System.out.println("BlackJack Dealer Wins");
			} else if (playerHandValue == 21 && dealerHandValue == 21) {
				System.out.println("BlackJack Push");
			}
		} else {
			if (playerHandValue > 21 && dealerHandValue < 21) {
				System.out.println(p.getName() + ", you have busted. House wins, game over");
			} else if (dealerHandValue > 21 && playerHandValue <= 21) {

				System.out.println(p.getName() + " Won! Dealer Busted");
			} else if (playerHandValue > dealerHandValue) {
				System.out.println(p.getName() + ", congratulations! You won.");
			} else if (dealerHandValue > playerHandValue) {
				showDealerHand("ALL");
				System.out.println(valueOfDealerHand());
				System.out.println("Dealer Wins");
			} else if (dealerHandValue == playerHandValue) {
				showDealerHand("ALL");
				System.out.println(valueOfDealerHand());
				System.out.println("Dealer Player push");
				// add money to player wallet
			}
		}
		// else if( playerHandValue > dealerHandValue) {
		// System.out.println(p.getName() + ", congratulations! You won.");
		// showPlayerHand();
		// System.out.println(playerHandValue);
		// }
		// else if( dealerHandValue > playerHandValue) {
		// System.out.println("Dealer, congratulations! You won.");
		// showDealerHand("ALL");
		// System.out.println(dealerHandValue);
		// }

	}

	public void showPlayerHand() {
		for (Card card : p.getHand().getCards()) {
			System.out.println(card);
		}

	}

	public void showDealerHand(String amount) {
		if (amount.equals("ONE")) {
			System.out.println("Dealer hand: " + dealer.get(0));
		} else if (amount.endsWith("ALL")) {
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
		for (Card c : p.getHand().getCards()) {
			if (c.getR().equals(Rank.ACE) && val > 21) {
				val = val - 10;
			}
		}
		return val;
	}

	public int valueOfDealerHand() {
		int val = 0;
		for (Card card : dealer) {
			val = val + card.getR().getValue();
		}

		for (Card c : dealer) {
			if (c.getR().equals(Rank.ACE) && val > 21) {
				val = val - 10;
			}
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
		}
	}

}
